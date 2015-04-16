package net.kevyporter.chromapixel.commands;

import net.kevyporter.chromapixel.extrahud.InfoHUD;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

public class ChangeColorCommand extends CommandBase {

	private boolean isOp(ICommandSender sender) {
		return (MinecraftServer.getServer().isSinglePlayer()) 
				|| (!(sender instanceof EntityPlayerMP))
				|| MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayerMP)sender).getGameProfile());
	}

	public String getCommandName() {
		return "color";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public boolean canSenderUseCommand(ICommandSender sender) {
		return true;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "/color <main,item> <color>";
	}

	public void processCommand(ICommandSender sender, String[] args) {
		try {
			if (args.length == 2)
		      {
		        if (args[0].equalsIgnoreCase("main"))
		        {
		          if (args[1].equalsIgnoreCase("dark_red")) {
		            InfoHUD.mainColor = EnumChatFormatting.DARK_RED;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("red")) {
		            InfoHUD.mainColor = EnumChatFormatting.RED;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("gold")) {
		            InfoHUD.mainColor = EnumChatFormatting.GOLD;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("yellow")) {
		            InfoHUD.mainColor = EnumChatFormatting.YELLOW;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("dark_green")) {
		            InfoHUD.mainColor = EnumChatFormatting.DARK_GREEN;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("green")) {
		            InfoHUD.mainColor = EnumChatFormatting.GREEN;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("aqua")) {
		            InfoHUD.mainColor = EnumChatFormatting.AQUA;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("dark_aqua")) {
		            InfoHUD.mainColor = EnumChatFormatting.DARK_AQUA;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("dark_blue")) {
		            InfoHUD.mainColor = EnumChatFormatting.DARK_BLUE;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("blue")) {
		            InfoHUD.mainColor = EnumChatFormatting.BLUE;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("light_purple")) {
		            InfoHUD.mainColor = EnumChatFormatting.LIGHT_PURPLE;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("dark_purple")) {
		            InfoHUD.mainColor = EnumChatFormatting.DARK_PURPLE;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("white")) {
		            InfoHUD.mainColor = EnumChatFormatting.WHITE;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("gray")) {
		            InfoHUD.mainColor = EnumChatFormatting.GRAY;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("dark_gray")) {
		            InfoHUD.mainColor = EnumChatFormatting.DARK_GRAY;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          } else if (args[1].equalsIgnoreCase("black")) {
		            InfoHUD.mainColor = EnumChatFormatting.BLACK;
		            new ChatMessageComposer("Set main color", InfoHUD.mainColor).send();
		          }
		        }
		        else if (args[0].equalsIgnoreCase("item")) {
		        	if (args[1].equalsIgnoreCase("dark_red")) {
			            InfoHUD.itemColor = EnumChatFormatting.DARK_RED;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("red")) {
			            InfoHUD.itemColor = EnumChatFormatting.RED;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("gold")) {
			            InfoHUD.itemColor = EnumChatFormatting.GOLD;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("yellow")) {
			            InfoHUD.itemColor = EnumChatFormatting.YELLOW;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("dark_green")) {
			            InfoHUD.itemColor = EnumChatFormatting.DARK_GREEN;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("green")) {
			            InfoHUD.itemColor = EnumChatFormatting.GREEN;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("aqua")) {
			            InfoHUD.itemColor = EnumChatFormatting.AQUA;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("dark_aqua")) {
			            InfoHUD.itemColor = EnumChatFormatting.DARK_AQUA;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("dark_blue")) {
			            InfoHUD.itemColor = EnumChatFormatting.DARK_BLUE;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("blue")) {
			            InfoHUD.itemColor = EnumChatFormatting.BLUE;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("light_purple")) {
			            InfoHUD.itemColor = EnumChatFormatting.LIGHT_PURPLE;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("dark_purple")) {
			            InfoHUD.itemColor = EnumChatFormatting.DARK_PURPLE;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("white")) {
			            InfoHUD.itemColor = EnumChatFormatting.WHITE;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("gray")) {
			            InfoHUD.itemColor = EnumChatFormatting.GRAY;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("dark_gray")) {
			            InfoHUD.itemColor = EnumChatFormatting.DARK_GRAY;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          } else if (args[1].equalsIgnoreCase("black")) {
			            InfoHUD.itemColor = EnumChatFormatting.BLACK;
			            new ChatMessageComposer("Set item color", InfoHUD.itemColor).send();
			          }
		          else
		          {
		            new ChatMessageComposer("Color List:", EnumChatFormatting.YELLOW).send();
		            new ChatMessageComposer("    DARK_RED", EnumChatFormatting.DARK_RED).send(false);
		            new ChatMessageComposer("    RED", EnumChatFormatting.RED).send(false);
		            new ChatMessageComposer("    GOLD", EnumChatFormatting.GOLD).send(false);
		            new ChatMessageComposer("    YELLOW", EnumChatFormatting.YELLOW).send(false);
		            new ChatMessageComposer("    DARK_GREEN", EnumChatFormatting.DARK_GREEN).send(false);
		            new ChatMessageComposer("    GREEN", EnumChatFormatting.GREEN).send(false);
		            new ChatMessageComposer("    AQUA", EnumChatFormatting.AQUA).send(false);
		            new ChatMessageComposer("    DARK_AQUA", EnumChatFormatting.DARK_AQUA).send(false);
		            new ChatMessageComposer("    DARK_BLUE", EnumChatFormatting.DARK_BLUE).send(false);
		            new ChatMessageComposer("    BLUE", EnumChatFormatting.BLUE).send(false);
		            new ChatMessageComposer("    LIGHT_PURPLE", EnumChatFormatting.LIGHT_PURPLE).send(false);
		            new ChatMessageComposer("    DARK_PURPLE", EnumChatFormatting.DARK_PURPLE).send(false);
		            new ChatMessageComposer("    WHITE", EnumChatFormatting.WHITE).send(false);
		            new ChatMessageComposer("    GRAY", EnumChatFormatting.GRAY).send(false);
		            new ChatMessageComposer("    DARK_GRAY", EnumChatFormatting.DARK_GRAY).send(false);
		            new ChatMessageComposer("    BLACK", EnumChatFormatting.BLACK).send(false);
		          }
		        }
		        else
		        {
		          new ChatMessageComposer("Color List:", EnumChatFormatting.YELLOW).send();
		          new ChatMessageComposer("    DARK_RED", EnumChatFormatting.DARK_RED).send(false);
		          new ChatMessageComposer("    RED", EnumChatFormatting.RED).send(false);
		          new ChatMessageComposer("    GOLD", EnumChatFormatting.GOLD).send(false);
		          new ChatMessageComposer("    YELLOW", EnumChatFormatting.YELLOW).send(false);
		          new ChatMessageComposer("    DARK_GREEN", EnumChatFormatting.DARK_GREEN).send(false);
		          new ChatMessageComposer("    GREEN", EnumChatFormatting.GREEN).send(false);
		          new ChatMessageComposer("    AQUA", EnumChatFormatting.AQUA).send(false);
		          new ChatMessageComposer("    DARK_AQUA", EnumChatFormatting.DARK_AQUA).send(false);
		          new ChatMessageComposer("    DARK_BLUE", EnumChatFormatting.DARK_BLUE).send(false);
		          new ChatMessageComposer("    BLUE", EnumChatFormatting.BLUE).send(false);
		          new ChatMessageComposer("    LIGHT_PURPLE", EnumChatFormatting.LIGHT_PURPLE).send(false);
		          new ChatMessageComposer("    DARK_PURPLE", EnumChatFormatting.DARK_PURPLE).send(false);
		          new ChatMessageComposer("    WHITE", EnumChatFormatting.WHITE).send(false);
		          new ChatMessageComposer("    GRAY", EnumChatFormatting.GRAY).send(false);
		          new ChatMessageComposer("    DARK_GRAY", EnumChatFormatting.DARK_GRAY).send(false);
		          new ChatMessageComposer("    BLACK", EnumChatFormatting.BLACK).send(false);
		        }
		      }
		      else if ((args.length == 1) && (args[0].equalsIgnoreCase("list")))
		      {
		        new ChatMessageComposer("Color List:", EnumChatFormatting.YELLOW).send();
		        new ChatMessageComposer("    DARK_RED", EnumChatFormatting.DARK_RED).send(false);
		        new ChatMessageComposer("    RED", EnumChatFormatting.RED).send(false);
		        new ChatMessageComposer("    GOLD", EnumChatFormatting.GOLD).send(false);
		        new ChatMessageComposer("    YELLOW", EnumChatFormatting.YELLOW).send(false);
		        new ChatMessageComposer("    DARK_GREEN", EnumChatFormatting.DARK_GREEN).send(false);
		        new ChatMessageComposer("    GREEN", EnumChatFormatting.GREEN).send(false);
		        new ChatMessageComposer("    AQUA", EnumChatFormatting.AQUA).send(false);
		        new ChatMessageComposer("    DARK_AQUA", EnumChatFormatting.DARK_AQUA).send(false);
		        new ChatMessageComposer("    DARK_BLUE", EnumChatFormatting.DARK_BLUE).send(false);
		        new ChatMessageComposer("    BLUE", EnumChatFormatting.BLUE).send(false);
		        new ChatMessageComposer("    LIGHT_PURPLE", EnumChatFormatting.LIGHT_PURPLE).send(false);
		        new ChatMessageComposer("    DARK_PURPLE", EnumChatFormatting.DARK_PURPLE).send(false);
		        new ChatMessageComposer("    WHITE", EnumChatFormatting.WHITE).send(false);
		        new ChatMessageComposer("    GRAY", EnumChatFormatting.GRAY).send(false);
		        new ChatMessageComposer("    DARK_GRAY", EnumChatFormatting.DARK_GRAY).send(false);
		        new ChatMessageComposer("    BLACK", EnumChatFormatting.BLACK).send(false);
		      }
		      else
		      {
		        new ChatMessageComposer("Usage: " + getCommandUsage(sender), EnumChatFormatting.RED).send(false);
		      }
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

}