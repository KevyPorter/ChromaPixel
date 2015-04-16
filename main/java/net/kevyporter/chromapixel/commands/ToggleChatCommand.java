package net.kevyporter.chromapixel.commands;

import java.util.ArrayList;
import java.util.List;

import net.kevyporter.chromapixel.listeners.ToggleChatListener;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ToggleChatCommand
extends CommandBase
{
	private boolean isOp(ICommandSender sender)
	{
		return (MinecraftServer.getServer().isSinglePlayer()) 
				|| (!(sender instanceof EntityPlayerMP))
				|| MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayerMP)sender).getGameProfile());
	}
		      
	public String getCommandName()
	{
		return "togglechat";
	}

	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	public boolean canSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	public String getCommandUsage(ICommandSender sender)
	{
		return "/togglechat <main, party, guild, pms>";
	}

	public List addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		List<String> tab = new ArrayList();
		tab.add("main");
		tab.add("party");
		tab.add("guild");
		tab.add("pms");
		return tab;
	}

	public void processCommand(ICommandSender sender, String[] args)
	{
		try
		{
			if (args.length == 1)
			{
				if (args[0].equalsIgnoreCase("main")) {
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/togglechat");
				} else if (args[0].equalsIgnoreCase("guild"))
				{
					if (ToggleChatListener.guildChat)
					{
						ToggleChatListener.guildChat = false;
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You will no longer see guild chat!"));
					}
					else
					{
						ToggleChatListener.guildChat = true;
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You will now see guild chat!"));
					}
				}
				else if (args[0].equalsIgnoreCase("party")) {
					if (ToggleChatListener.partyChat)
					{
						ToggleChatListener.partyChat = false;
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You will no longer see party chat!"));
					}
					else
					{
						ToggleChatListener.partyChat = true;
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You will now see party chat!"));
					}
				}
				else if (args[0].equalsIgnoreCase("pms")) {
					if (ToggleChatListener.pmsChat)
					{
						ToggleChatListener.pmsChat = false;
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You will no longer see PMs!"));
					}
					else
					{
						ToggleChatListener.pmsChat = true;
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You will now see PMs."));
					}
				}
			}
			else {
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: " + getCommandUsage(sender)));
			}
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

}