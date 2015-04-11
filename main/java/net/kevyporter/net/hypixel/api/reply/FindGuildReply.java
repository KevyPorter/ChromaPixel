package net.kevyporter.net.hypixel.api.reply;

public class FindGuildReply extends AbstractReply {
    private String guild;

    public String getGuild() {
        return guild;
    }

    @Override
    public String toString() {
        return "FindGuildReply{" +
                "guild=" + guild +
                ",super=" + super.toString() + "}";
    }
}
