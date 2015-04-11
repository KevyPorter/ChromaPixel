package net.kevyporter.chromapixel.api.interaction.representations;

import com.google.gson.JsonObject;

public class Player {
    protected JsonObject player;
    
    protected JsonObject stats;
    
    protected String uuid;
    protected String playername;
    protected String displayname;
    protected String packageRank;
    
    protected int vanityTokens;
    protected int tournamentTokens;
    protected int networkLevel;
    protected int networkExp;
    protected int karma;
    
    protected int tipsSent;
    protected int thanksSent;
    protected int thanksReceived;
    protected int tipsReceived;
    protected long timePlaying;
    protected long lastLogin;
    protected long firstLogin;
    
    public JsonObject getStats() {
        return stats;
    }
}
