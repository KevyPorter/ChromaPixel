package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class VampireZStatsDisplayer
  extends StatsDisplayer
{
  public VampireZStatsDisplayer(String name)
  {
    super(name);
    getStats();
  }
  
  public void displayStatsInChat()
  {
    getStats();
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
    new ChatMessageComposer(this.playerName + "'s stats in " + GameType.VAMPIREZ.getName(), EnumChatFormatting.GREEN).send();
    new ChatMessageComposer("     Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.coins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Human Wins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.wins, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Human Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.hkills, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Vampire Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.vkills, EnumChatFormatting.GOLD)).send(false);
    new ChatMessageComposer("     Zombie Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.zkills, EnumChatFormatting.GOLD)).send(false);
    ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
  }
  
  private int zkills = 0;
  private int vkills = 0;
  private int wins = 0;
  private int hkills = 0;
  private int coins = 0;
  
  private void getStats()
  {
    try
    {
      this.coins = this.statistics.get("VampireZ").getAsJsonObject().get("coins").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.hkills = this.statistics.get("VampireZ").getAsJsonObject().get("human_kills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.vkills = this.statistics.get("VampireZ").getAsJsonObject().get("vampire_kills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.zkills = this.statistics.get("VampireZ").getAsJsonObject().get("zombie_kills").getAsInt();
    }
    catch (Exception e) {}
    try
    {
      this.wins = this.statistics.get("VampireZ").getAsJsonObject().get("human_wins").getAsInt();
    }
    catch (Exception e) {}
  }
}
