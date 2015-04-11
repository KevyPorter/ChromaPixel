package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class ArcadeStatsDisplayer
  extends StatsDisplayer
{
  public ArcadeStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.ARCADE.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("     Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.coins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Blocking Dead", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.bdkills, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.bdwins, EnumChatFormatting.GOLD)))).send(false);
    new ChatMessageComposer("     Dragon Wars", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.dwkills, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.dwwins, EnumChatFormatting.GOLD)))).send(false);
    new ChatMessageComposer("     Bounty Hunters", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.bhkills, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.bhwins, EnumChatFormatting.GOLD)))).send(false);
    new ChatMessageComposer("     Throw Out", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.tokills, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.towins, EnumChatFormatting.GOLD)))).send(false);
    new ChatMessageComposer("     Partygames1 Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.pg1wins, EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Partygames2 Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.pg2wins, EnumChatFormatting.GOLD)))).send(false);
    new ChatMessageComposer("     Enderspleef", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.eswins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Farmhunt", EnumChatFormatting.GREEN).send(false);
    new ChatMessageComposer("     Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.fhwins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Poop Collected: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.shit, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Creeper Attack", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.fhwins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Max Wave: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.cmaxwave, EnumChatFormatting.GOLD)).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int shit = 0;
  private int cmaxwave = 0;
  private int towins = 0;
  private int pg2wins = 0;
  private int pg1wins = 0;
  private int bhwins = 0;
  private int fhwins = 0;
  private int eswins = 0;
  private int dwwins = 0;
  private int bdwins = 0;
  private int tokills = 0;
  private int bhkills = 0;
  private int dwkills = 0;
  private int bdkills = 0;
  private int coins = 0;
  
  private void getStats()
  {
    try
    {
      this.coins = this.statistics.get("Arcade").getAsJsonObject().get("coins").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.bdkills = this.statistics.get("Arcade").getAsJsonObject().get("kills_dayone").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.bdwins = this.statistics.get("Arcade").getAsJsonObject().get("wins_dayone").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.bhkills = this.statistics.get("Arcade").getAsJsonObject().get("kills_oneinthequiver").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.bhwins = this.statistics.get("Arcade").getAsJsonObject().get("wins_oneinthequiver").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.cmaxwave = this.statistics.get("Arcade").getAsJsonObject().get("max_wave").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.dwkills = this.statistics.get("Arcade").getAsJsonObject().get("kills_dragonwars2").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.dwwins = this.statistics.get("Arcade").getAsJsonObject().get("wins_dragonwars2").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.eswins = this.statistics.get("Arcade").getAsJsonObject().get("wins_ender").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.fhwins = this.statistics.get("Arcade").getAsJsonObject().get("wins_farm_hunt").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.pg1wins = this.statistics.get("Arcade").getAsJsonObject().get("wins_party").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.pg2wins = this.statistics.get("Arcade").getAsJsonObject().get("wins_party_2").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.tokills = this.statistics.get("Arcade").getAsJsonObject().get("kills_throw_out").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.towins = this.statistics.get("Arcade").getAsJsonObject().get("wins_throw_out").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.shit = this.statistics.get("Arcade").getAsJsonObject().get("poop_collected").getAsInt();
    }
    catch (Exception e) {}
  }
}
