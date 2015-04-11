package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class TNTGamesStatsDisplayer
  extends StatsDisplayer
{
  public TNTGamesStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.TNTGAMES.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("     Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.coins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Bowspleef Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.bwins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     TNTRun Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.rwins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     TNTTag Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.twins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Wizards Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.wwins, EnumChatFormatting.GOLD)).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int twins = 0;
  private int wwins = 0;
  private int bwins = 0;
  private int rwins = 0;
  private int coins = 0;
  
  private void getStats()
  {
    try
    {
      this.coins = this.statistics.get("TNTGames").getAsJsonObject().get("coins").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.rwins = this.statistics.get("TNTGames").getAsJsonObject().get("wins_tntrun").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.bwins = this.statistics.get("TNTGames").getAsJsonObject().get("wins_bowspleef").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.wwins = this.statistics.get("TNTGames").getAsJsonObject().get("wins_capture").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.twins = this.statistics.get("TNTGames").getAsJsonObject().get("wins_tnttag").getAsInt();
    }
    catch (Exception e) {}
  }
}
