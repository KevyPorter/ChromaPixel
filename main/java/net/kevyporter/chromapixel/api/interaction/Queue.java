package net.kevyporter.chromapixel.api.interaction;

import java.util.ArrayList;
import java.util.UUID;

import net.kevyporter.chromapixel.ChromaPixelConfig;
import net.kevyporter.chromapixel.ChromaPixelMod;
import net.kevyporter.chromapixel.api.interaction.callbacks.ApiKeyLoadedCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.BoosterResponseCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.FriendResponseCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.PlayerResponseCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.SessionResponseCallback;
import net.kevyporter.net.hypixel.api.HypixelAPI;

import com.google.gson.Gson;


public class Queue implements ApiKeyLoadedCallback{
    private static Queue instance;
    private ApiKeyHandler keyHandler;
    public boolean apiEnabled = false;
    private HypixelAPI api;
    // even though the queue doesn't need it, it holds a gson instance which is used by QueueEntry
    private Gson gson;
    private ArrayList<QueueEntry> queue;
    private boolean isLocked;
    // used to limit the amount of requests, every request produces heat
    private int heat;
    // current values limit the queue to 1 request every 5 seconds on average
    private static final int HEAT_PER_REQUEST = 5;
    private static final int HEAT_MAXIMUM = 100;
    private static final int HEAT_COOLDOWN_PER_SECOND = 1;
    // keeps track of last second, so it can reduce the heat
    private long sec;
    // saves when the mod last asked the user for a key
    private long lastKeyNotice;
    private static final int MIN_TIME_BETWEEN_KEY_NOTICES = 60000; // = 1 min
    private static final int API_DISABLED_TIMEOUT = 15000;
    public Queue() {
        instance = this;
        this.queue = new ArrayList<QueueEntry>();
        // Instantiate the API first, so the key can't be loaded when the api is still null
        this.api = HypixelAPI.getInstance();
        this.gson = new Gson();
        this.keyHandler = new ApiKeyHandler(this);
    }

    public void onChatMessage(String textMessage) {
        this.keyHandler.onChatMessage(textMessage);
    }
    
    public void onClientTick() {
        // request the key if necessary
        if(ChromaPixelConfig.useAPI && !this.apiEnabled && !this.queue.isEmpty()) {
            // if the api is disabled, the requests will expire
            if(this.queue.get(0).getCreationTime() + API_DISABLED_TIMEOUT < System.currentTimeMillis()) {
                this.queue.get(0).cancel();
                this.queue.remove(0);
            }
            if(this.lastKeyNotice == 0 || System.currentTimeMillis() > this.lastKeyNotice + MIN_TIME_BETWEEN_KEY_NOTICES) {
                this.lastKeyNotice = System.currentTimeMillis();
                this.keyHandler.requestApiKey();
            }
        }
        
        // run the queue
        if(ChromaPixelConfig.useAPI && this.apiEnabled && !this.queue.isEmpty() && !this.isLocked && this.heat < HEAT_MAXIMUM + HEAT_PER_REQUEST) {
            QueueEntry entry = this.queue.get(0);
            entry.run();
            this.queue.remove(0);
            this.heat += HEAT_PER_REQUEST;
        }
        
        long currentSec = System.currentTimeMillis() / 1000;
        if(this.sec < currentSec) {
            this.heat -= HEAT_COOLDOWN_PER_SECOND * (currentSec - this.sec);
            this.sec = currentSec;
        }
    }

    @Override
    public void ApiKeyLoaded(boolean failed, String key) {
        if(failed) {
            this.apiEnabled = false;
        } else {
            this.api.setApiKey(UUID.fromString(key));
            this.apiEnabled = true;
        }
    }
    
    public HypixelAPI getAPI() {
        return this.api;
    }
    
    public Gson getGson() {
        return gson;
    }
    
    /**
     * Queues a Booster request.
     * @param callback
     */
    public void getBoosters(BoosterResponseCallback callback) {
        if(ChromaPixelConfig.useAPI) {
            this.queue.add(new QueueEntry(callback));
        } else {
            callback.onBoosterResponse(null);
        }
    }
    
    /**
     * Queues a Session request.
     * @param callback
     */
    public void getSession(SessionResponseCallback callback, String player) {
        if(ChromaPixelConfig.useAPI) {
            this.queue.add(new QueueEntry(callback, player));
        } else {
            callback.onSessionRespone(null);
        }
    }
    
    /**
     * Queues a Friends request.
     * @param callback
     */
    public void getFriends(FriendResponseCallback callback, String player) {
        if(ChromaPixelConfig.useAPI) {
            this.queue.add(new QueueEntry(callback, player));
        } else {
            callback.onFriendResponse(null);
        }
    }
    
    /**
     * Queues a Player request.
     * @param callback
     * @param player
     */
    public void getPlayer(PlayerResponseCallback callback, String player) {
        if(ChromaPixelConfig.useAPI) {
            this.queue.add(new QueueEntry(callback, player));
        } else {
            callback.onPlayerResponse(null);
        }
    }
    
    public void reportFailure(Throwable failCause, boolean secondTry) {
        ChromaPixelMod logger = ChromaPixelMod.instance();
        if(secondTry) {
            logger.logError("An API requst failed on the second try. Giving up...");
            failCause.printStackTrace();
        } else {
            logger.logWarn("An API request failed. Retrying...");
            failCause.printStackTrace();
        }
    }
    
    /**
     * Called when a request is finished to allow the next request to enter
     */
    public void unlockQueue() {
        this.isLocked = false;
    }
    
    public static Queue getInstance() {
        return instance;
    }
}