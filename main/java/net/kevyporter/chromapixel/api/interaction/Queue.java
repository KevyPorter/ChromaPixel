package net.kevyporter.chromapixel.api.interaction;

import java.util.ArrayList;
import java.util.UUID;

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
	private Gson gson;
	private ArrayList<QueueEntry> queue;
	private boolean isLocked;
	private int heat;
	private static final int HEAT_PER_REQUEST = 5;
	private static final int HEAT_MAXIMUM = 100;
	private static final int HEAT_COOLDOWN_PER_SECOND = 1;
	private long sec;
	private long lastKeyNotice;
	private static final int MIN_TIME_BETWEEN_KEY_NOTICES = 60000; // = 1 min
			private static final int API_DISABLED_TIMEOUT = 15000;
			public Queue() {
				instance = this;
				this.queue = new ArrayList<QueueEntry>();
				this.api = HypixelAPI.getInstance();
				this.gson = new Gson();
				this.keyHandler = new ApiKeyHandler(this);
			}

			public void onChatMessage(String textMessage) {
				this.keyHandler.onChatMessage(textMessage);
			}

			public void onClientTick() {
				if(!this.apiEnabled && !this.queue.isEmpty()) {
					if(this.queue.get(0).getCreationTime() + API_DISABLED_TIMEOUT < System.currentTimeMillis()) {
						this.queue.get(0).cancel();
						this.queue.remove(0);
					}
					if(this.lastKeyNotice == 0 || System.currentTimeMillis() > this.lastKeyNotice + MIN_TIME_BETWEEN_KEY_NOTICES) {
						this.lastKeyNotice = System.currentTimeMillis();
						this.keyHandler.requestApiKey();
					}
				}

				if(this.apiEnabled && !this.queue.isEmpty() && !this.isLocked && this.heat < HEAT_MAXIMUM + HEAT_PER_REQUEST) {
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

			public void getBoosters(BoosterResponseCallback callback) {
				if(apiEnabled) {
					this.queue.add(new QueueEntry(callback));
				} else {
					callback.onBoosterResponse(null);
				}
			}

			public void getSession(SessionResponseCallback callback, String player) {
				if(apiEnabled) {
					this.queue.add(new QueueEntry(callback, player));
				} else {
					callback.onSessionRespone(null);
				}
			}

			public void getFriends(FriendResponseCallback callback, String player) {
				if(apiEnabled) {
					this.queue.add(new QueueEntry(callback, player));
				} else {
					callback.onFriendResponse(null);
				}
			}

			public void getPlayer(PlayerResponseCallback callback, String player) {
				if(apiEnabled) {
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

			public void unlockQueue() {
				this.isLocked = false;
			}

			public static Queue getInstance() {
				return instance;
			}
}