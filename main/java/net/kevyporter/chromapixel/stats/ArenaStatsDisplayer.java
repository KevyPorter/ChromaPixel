package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class ArenaStatsDisplayer
  extends StatsDisplayer
{
  public ArenaStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.ARENA.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("     2v2", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.v2kills, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.v2wins, EnumChatFormatting.GOLD)))).send(false);
    new ChatMessageComposer("     4v4", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.v4kills, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.v4wins, EnumChatFormatting.GOLD)))).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int v4wins = 0;
  private int v4kills = 0;
  private int v2wins;
  private int v2kills = 0;
  
  private void getStats()
  {
    try
    {
      this.v2kills = this.statistics.get("Arena").getAsJsonObject().get("kills_2v2").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.v2wins = this.statistics.get("Arena").getAsJsonObject().get("wins_2v2").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.v4kills = this.statistics.get("Arena").getAsJsonObject().get("kills_4v4").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.v4wins = this.statistics.get("Arena").getAsJsonObject().get("wins_4v4").getAsInt();
    }
    catch (Exception e) {}
  }
}
