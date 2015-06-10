package net.kevyporter.chromapixel.commands;

import java.util.ArrayList;
import java.util.List;

import net.kevyporter.chromapixel.dmgreduction.DmgReductionCalc;
import net.kevyporter.chromapixel.dmgreduction.PlayerArmorInfo;
import net.kevyporter.chromapixel.listeners.ToggleChatListener;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class DamageReductionCommand extends CommandBase
{
	private boolean isOp(ICommandSender sender)
	{
		return (MinecraftServer.getServer().isSinglePlayer()) 
				|| (!(sender instanceof EntityPlayerMP))
				|| MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayerMP)sender).getGameProfile());
	}

	public String getCommandName()
	{
		return "armor";
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
		return "/armor";
	}

	public void processCommand(ICommandSender sender, String[] args)
	{
		try
		{
			if (args.length == 0)
			{
				DmgReductionCalc.getReduction();
				new ChatMessageComposer("Minimum damage reduction ", EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer("" + DmgReductionCalc.armorReduct.get(0), EnumChatFormatting.LIGHT_PURPLE).appendMessage(new ChatMessageComposer("%", EnumChatFormatting.GRAY))).send(false);
				new ChatMessageComposer("Maximum damage reduction ", EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer("" + DmgReductionCalc.armorReduct.get(1), EnumChatFormatting.LIGHT_PURPLE).appendMessage(new ChatMessageComposer("%", EnumChatFormatting.GRAY))).send(false);
				new ChatMessageComposer("Average damage reduction ", EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer("" + DmgReductionCalc.armorReduct.get(2), EnumChatFormatting.LIGHT_PURPLE).appendMessage(new ChatMessageComposer("%", EnumChatFormatting.GRAY))).send(false);
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