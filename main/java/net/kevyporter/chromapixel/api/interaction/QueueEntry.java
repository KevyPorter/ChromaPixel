package net.kevyporter.chromapixel.api.interaction;

import java.util.ArrayList;

import net.kevyporter.chromapixel.api.interaction.callbacks.BoosterResponseCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.FriendResponseCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.PlayerResponseCallback;
import net.kevyporter.chromapixel.api.interaction.callbacks.SessionResponseCallback;
import net.kevyporter.chromapixel.api.interaction.representations.Booster;
import net.kevyporter.chromapixel.api.interaction.representations.Friend;
import net.kevyporter.chromapixel.api.interaction.representations.Player;
import net.kevyporter.chromapixel.api.interaction.representations.Session;
import net.kevyporter.net.hypixel.api.HypixelAPI;
import net.kevyporter.net.hypixel.api.reply.BoostersReply;
import net.kevyporter.net.hypixel.api.reply.FriendsReply;
import net.kevyporter.net.hypixel.api.reply.PlayerReply;
import net.kevyporter.net.hypixel.api.reply.SessionReply;
import net.kevyporter.net.hypixel.api.util.Callback;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class QueueEntry {
    private boolean isSecondTry;
    private long creationTime;
    
    private BoosterResponseCallback boosterCallback;
    private SessionResponseCallback sessionCallback;
    private FriendResponseCallback friendCallback;
    private PlayerResponseCallback playerCallback;
    private String player;
    
    public QueueEntry(BoosterResponseCallback callback) {
        this.boosterCallback = callback;
        this.creationTime = System.currentTimeMillis();
    }
    
    public QueueEntry(SessionResponseCallback callback, String player) {
        this.sessionCallback = callback;
        this.player = player;
        this.creationTime = System.currentTimeMillis();
    }
    
    public QueueEntry(FriendResponseCallback callback, String player) {
        this.friendCallback = callback;
        this.player = player;
        this.creationTime = System.currentTimeMillis();
    }
    
    public QueueEntry(PlayerResponseCallback callback, String player) {
        this.playerCallback = callback;
        this.player = player;
        this.creationTime = System.currentTimeMillis();
    }
    
    public void run() {
        if(this.boosterCallback != null) {
            this.doBoosterRequest();
        } else if(this.sessionCallback != null) {
            this.doSessionRequest();
        } else if(this.friendCallback != null) {
            this.doFriendRequest();
        } else if(this.playerCallback != null) {
            this.doPlayerRequest();
        }
    }
    
    private void failed(Throwable failCause) {
        Queue.getInstance().reportFailure(failCause, this.isSecondTry);
        if(this.isSecondTry) {
            this.cancel();
        } else {
            this.isSecondTry = true;
            this.run();
        }
    }
    
    public void cancel() {
        if(this.boosterCallback != null) {
            this.boosterCallback.onBoosterResponse(null);
        } else if(this.sessionCallback != null) {
            this.sessionCallback.onSessionRespone(null);
        } else if(this.friendCallback != null) {
            this.friendCallback.onFriendResponse(null);
        }
        Queue.getInstance().unlockQueue();
    }
    
    public long getCreationTime() {
        return this.creationTime;
    }
    
    private void doBoosterRequest() {
        HypixelAPI api = Queue.getInstance().getAPI();
        api.getBoosters(new Callback<BoostersReply>(BoostersReply.class) {
            @Override
            public void callback(Throwable failCause, BoostersReply result) {
                if(failCause != null) {
                    failed(failCause);
                } else {
                    ArrayList<Booster> boosters = new ArrayList<Booster>();
                    Gson gson = Queue.getInstance().getGson();
                    for(JsonElement e : result.getBoosters()) {
                        Booster b = gson.fromJson(e, Booster.class);
                        boosters.add(b);
                    }
                    boosterCallback.onBoosterResponse(boosters);
                    Queue.getInstance().unlockQueue();
                }
            }
        });
    }
    
    private void doSessionRequest() {
        HypixelAPI api = Queue.getInstance().getAPI();
        api.getSession(this.player, new Callback<SessionReply>(SessionReply.class) {
            @Override
            public void callback(Throwable failCause, SessionReply result) {
                if (failCause != null) {
                    failed(failCause);
                } else {
                    Gson gson = Queue.getInstance().getGson();
                    Session s = gson.fromJson(result.getSession(), Session.class);
                    if(s == null) {
                        s = new Session();
                    }
                    s.setSessionOwner(player);
                    sessionCallback.onSessionRespone(s);
                    Queue.getInstance().unlockQueue();
                }
            }
        });
    }
    
    private void doFriendRequest() {
        HypixelAPI api = Queue.getInstance().getAPI();
        api.getFriends(this.player, new Callback<FriendsReply>(FriendsReply.class) {
            
            @Override
            public void callback(Throwable failCause, FriendsReply result) {
                if (failCause != null) {
                    failed(failCause);
                } else {
                    ArrayList<Friend> friends = new ArrayList<Friend>();
                    Gson gson = Queue.getInstance().getGson();
                    for(JsonElement e : result.getRecords()) {
                        Friend f = gson.fromJson(e, Friend.class);
                        f.setPlayer(player);
                        friends.add(f);
                    }
                    friendCallback.onFriendResponse(friends);
                    Queue.getInstance().unlockQueue();
                }
            }
        });
    }
    
    private void doPlayerRequest() {
        HypixelAPI api = Queue.getInstance().getAPI();
        api.getPlayer(player, null ,new Callback<PlayerReply>(PlayerReply.class) {
            @Override
            public void callback(Throwable failCause, PlayerReply result) {
                if(failCause != null) {
                    failed(failCause);
                } else {
                    Gson gson = Queue.getInstance().getGson();
                    Player player = gson.fromJson(result.getPlayer(), Player.class);
                    playerCallback.onPlayerResponse(player);
                    Queue.getInstance().unlockQueue();
                }
            }
        });
    }
}
