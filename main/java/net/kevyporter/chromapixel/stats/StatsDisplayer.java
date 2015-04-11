package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.ChromaPixelMod;
import net.kevyporter.chromapixel.api.interaction.Queue;
import net.kevyporter.chromapixel.api.interaction.callbacks.PlayerResponseCallback;
import net.kevyporter.chromapixel.api.interaction.representations.Player;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.minecraft.util.EnumChatFormatting;

public abstract class StatsDisplayer
  implements PlayerResponseCallback
{
  protected String playerName;
  protected JsonObject statistics;
  protected boolean shouldPrint;
  
  protected StatsDisplayer(String playerName)
  {
    this.playerName = playerName;
    Queue.getInstance().getPlayer(this, playerName);
  }
  
  public void display()
  {
    if (this.statistics != null) {
      try
      {
        displayStatsInChat();
      }
      catch (Exception e)
      {
        ChromaPixelMod.instance().logError("Failed to display stats.");
        e.printStackTrace();
      }
    } else {
      this.shouldPrint = true;
    }
  }
  
  public void onPlayerResponse(Player player)
  {
    if (player != null)
    {
      if (player.getStats() != null)
      {
        this.statistics = player.getStats();
        if (this.shouldPrint) {
          display();
        }
      }
      else
      {
        failed();
      }
    }
    else
    {
      failed();
      return;
    }
  }
  
  private void failed()
  {
    new ChatMessageComposer("Failed to load stats for: " + this.playerName + "!", EnumChatFormatting.RED).send();
  }
  
  protected abstract void displayStatsInChat();
}
