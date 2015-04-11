package net.kevyporter.net.hypixel.api;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.kevyporter.net.hypixel.api.http.HTTPClient;
import net.kevyporter.net.hypixel.api.reply.BoostersReply;
import net.kevyporter.net.hypixel.api.reply.FindGuildReply;
import net.kevyporter.net.hypixel.api.reply.FriendsReply;
import net.kevyporter.net.hypixel.api.reply.GuildReply;
import net.kevyporter.net.hypixel.api.reply.KeyReply;
import net.kevyporter.net.hypixel.api.reply.PlayerReply;
import net.kevyporter.net.hypixel.api.reply.SessionReply;
import net.kevyporter.net.hypixel.api.util.APIUtil;
import net.kevyporter.net.hypixel.api.util.Callback;
import net.kevyporter.net.hypixel.api.util.HypixelAPIException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.base.Preconditions;

public class HypixelAPI {
    private static final String BASE_URL = "https://api.hypixel.net/";
    private static HypixelAPI instance;
    private final ReentrantReadWriteLock lock;
    private final HTTPClient httpClient;
    private UUID apiKey;
    private HypixelAPI() {
        lock = new ReentrantReadWriteLock();
        httpClient = new HTTPClient();
    }

    public static HypixelAPI getInstance() {
        if (instance == null) {
            instance = new HypixelAPI();
        }
        return instance;
    }

    public void finish() {
        instance = null;
    }

    public void setApiKey(UUID apiKey) {
        Preconditions.checkNotNull(apiKey);
        lock.writeLock().lock();
        try {
            this.apiKey = apiKey;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void getKeyInfo(Callback<KeyReply> callback) {
        getKeyInfo(apiKey, callback);
    }

    public void getKeyInfo(UUID apiKey, Callback<KeyReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                httpClient.get(BASE_URL + "key?key=" + apiKey.toString(), callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void findGuild(String name, String player, Callback<FindGuildReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                String args;
                if (name != null) {
                    args = "byName=" + StringEscapeUtils.escapeHtml4(name);
                } else if (player != null) {
                    args = "byPlayer=" + StringEscapeUtils.escapeHtml4(player);
                } else {
                    callback.callback(new HypixelAPIException("Neither name nor player was provided!"), null);
                    return;
                }
                httpClient.get(BASE_URL + "findGuild?key=" + apiKey.toString() + "&" + args, callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void getGuild(String id, Callback<GuildReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (id == null) {
                    callback.callback(new HypixelAPIException("Guild id wasn't provided!"), null);
                } else {
                    httpClient.get(BASE_URL + "guild?key=" + apiKey.toString() + "&id=" + StringEscapeUtils.escapeHtml4(id), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void getBoosters(Callback<BoostersReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                httpClient.get(BASE_URL + "boosters?key=" + apiKey.toString(), callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void getFriends(String player, Callback<FriendsReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (player == null) {
                    callback.callback(new HypixelAPIException("No player was provided!"), null);
                } else {
                    httpClient.get(BASE_URL + "friends?key=" + apiKey.toString() + "&player=" + StringEscapeUtils.escapeHtml4(player), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void getSession(String player, Callback<SessionReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                if (player == null) {
                    callback.callback(new HypixelAPIException("No player was provided!"), null);
                } else {
                    httpClient.get(BASE_URL + "session?key=" + apiKey.toString() + "&player=" + StringEscapeUtils.escapeHtml4(player), callback);
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void getPlayer(String player, UUID uuid, Callback<PlayerReply> callback) {
        lock.readLock().lock();
        try {
            if (doKeyCheck(callback)) {
                String args;
                if (player != null) {
                    args = "name=" + StringEscapeUtils.escapeHtml4(player);
                } else if (uuid != null) {
                    args = "uuid=" + APIUtil.stripDashes(uuid);
                } else {
                    callback.callback(new HypixelAPIException("Neither player nor uuid was provided!"), null);
                    return;
                }
                httpClient.get(BASE_URL + "player?key=" + apiKey.toString() + "&" + args, callback);
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    private boolean doKeyCheck(Callback<?> callback) {
        if (apiKey == null) {
            callback.callback(new HypixelAPIException("API key hasn't been set yet!"), null);
            return false;
        } else {
            return true;
        }
    }
}
