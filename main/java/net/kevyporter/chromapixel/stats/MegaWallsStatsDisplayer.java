package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class MegaWallsStatsDisplayer
  extends StatsDisplayer
{
  public MegaWallsStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.WALLS3.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("     Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.coins, EnumChatFormatting.GOLD)).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN)).appendMessage(new ChatMessageComposer("" + this.wins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.kills, EnumChatFormatting.GOLD)).appendMessage(new ChatMessageComposer(" Final Kills: ", EnumChatFormatting.GREEN)).appendMessage(new ChatMessageComposer("" + this.fkills, EnumChatFormatting.GOLD)).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int wins = 0;
  private int fkills = 0;
  private int kills = 0;
  private int coins = 0;
  
  private void getStats()
  {
    try
    {
      this.coins = this.statistics.get("Walls3").getAsJsonObject().get("coins").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.kills = this.statistics.get("Walls3").getAsJsonObject().get("kills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.fkills = this.statistics.get("Walls3").getAsJsonObject().get("finalKills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.wins = this.statistics.get("Walls3").getAsJsonObject().get("wins").getAsInt();
    }
    catch (Exception e) {}
  }
}
