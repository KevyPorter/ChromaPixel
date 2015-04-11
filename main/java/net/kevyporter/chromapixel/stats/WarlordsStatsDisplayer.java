package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class WarlordsStatsDisplayer
  extends StatsDisplayer
{
  public WarlordsStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.BATTLEGROUND.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("     Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.coins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.kills, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Assists: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.kills, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     CTF Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.ctfwins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     DOM Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.domwins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Weapons Repaired: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.repair, EnumChatFormatting.GOLD)).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int coins = 0;
  private int repair = 0;
  private int domwins = 0;
  private int ctfwins = 0;
  private int assists = 0;
  private int kills = 0;
  
  private void getStats()
  {
    try
    {
      this.coins = this.statistics.get("Battleground").getAsJsonObject().get("coins").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.kills = this.statistics.get("Battleground").getAsJsonObject().get("kills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.assists = this.statistics.get("Battleground").getAsJsonObject().get("assists").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.ctfwins = this.statistics.get("Battleground").getAsJsonObject().get("wins_capturetheflag").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.domwins = this.statistics.get("Battleground").getAsJsonObject().get("wins_domination").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.repair = this.statistics.get("Battleground").getAsJsonObject().get("repaired").getAsInt();
    }
    catch (Exception e) {}
  }
}
