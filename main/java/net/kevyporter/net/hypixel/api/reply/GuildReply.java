package net.kevyporter.net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GuildReply extends AbstractReply {
    private JsonElement guild;

    public JsonObject getGuild() {
        if(guild.isJsonNull()) {
            return null;
        } else {
            return guild.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "GuildReply{" +
                "guild=" + guild +
                ",super=" + super.toString() + "}";
    }
}
