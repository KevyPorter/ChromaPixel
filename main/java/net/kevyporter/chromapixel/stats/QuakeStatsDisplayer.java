package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class QuakeStatsDisplayer
  extends StatsDisplayer
{
  public QuakeStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.QUAKECRAFT.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.coins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.kills, EnumChatFormatting.GOLD)).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN)).appendMessage(new ChatMessageComposer("" + this.wins, EnumChatFormatting.GOLD)).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int kills = 0;
  private int wins = 0;
  private int coins = 0;
  
  private void getStats()
  {
    try
    {
      this.coins = this.statistics.get("Quake").getAsJsonObject().get("coins").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.kills = this.statistics.get("Quake").getAsJsonObject().get("kills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.wins = this.statistics.get("Quake").getAsJsonObject().get("wins").getAsInt();
    }
    catch (Exception e) {}
  }
}
