package net.kevyporter.chromapixel.commands;

import net.kevyporter.chromapixel.listeners.AfkListener;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class AfkCommand extends CommandBase
{
	private boolean isOp(ICommandSender sender)
	{
		return (MinecraftServer.getServer().isSinglePlayer()) 
				|| (!(sender instanceof EntityPlayerMP))
				|| MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayerMP)sender).getGameProfile());
	}

	public String getCommandName()
	{
		return "afk";
	}

	public int getRequiredPermissionLevel()
	{
		return 0;
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	public String getCommandUsage(ICommandSender sender)
	{
		return "/afk [message]";
	}

	public void processCommand(ICommandSender sender, String[] args)
	{
		try
		{
			if (args.length >= 0)
			{
				if (AfkListener.isAfk)
				{
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "You are no longer AFK!"));
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "While you were AFK you received these messages:"));
					for (int i = 0; i < AfkListener.afkMessages.size(); i++) {
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText((String)AfkListener.afkMessages.get(i)));
					}
					AfkListener.afkMessages.clear();
					AfkListener.isAfk = false;
				}
				else if (args.length == 0)
				{
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "You are now AFK! AFK message set to: I'm currently AFK message me later!"));
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "You can change this message by typing /afk <message>."));
					AfkListener.afkMessage = "I'm currently AFK message me later!";
					AfkListener.isAfk = true;
				}
				else
				{
					StringBuilder message = new StringBuilder();
					for (int i = 0; i < args.length; i++)
					{
						if (message.length() > 0) {
							message.append(" ");
						}
						message.append(args[i]);
					}
					AfkListener.afkMessage = message.toString();
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "You are now AFK! AFK message set to: " + message.toString()));
					AfkListener.isAfk = true;
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


