package net.kevyporter.net.hypixel.api.reply;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SessionReply extends AbstractReply {
    private JsonElement session;

    public JsonObject getSession() {
        if(session.isJsonNull()) {
            return null;
        } else {
            return session.getAsJsonObject();
        }
    }

    @Override
    public String toString() {
        return "SessionReply{" +
                "session=" + session +
                ",super=" + super.toString() + "}";
    }
}