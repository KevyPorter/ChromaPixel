package net.kevyporter.chromapixel.api.interaction.representations;

import java.util.ArrayList;

import net.kevyporter.net.hypixel.api.util.GameType;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class Session {
    private String sessionOwner;
    
    private String _id;
    private String gameType;
    private JsonArray players;
    private ArrayList<String> playersArray;
    private String server;
    
    public String getID() {
        return this._id;
    }
    
    public GameType getGameType() {
        return GameType.fromDatabase(this.gameType);
    }
    
    public String getServer() {
        return this.server;
    }
    
    public ArrayList<String> getPlayers() {
        if(this.playersArray == null) {
            this.playersArray = new ArrayList<String>();
            for(JsonElement s : this.players) {
                if(s.isJsonPrimitive()) {
                    this.playersArray.add(s.getAsString());
                }
            }
        }
            return this.playersArray;
    }
    
    public String getSessionOwner() {
        return this.sessionOwner;
    }
    
    public void setSessionOwner(String owner) {
        this.sessionOwner = owner;
    }
}