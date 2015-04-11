package net.kevyporter.chromapixel.listeners;

import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TeamAcceptListener {

	private String TEAM_REQUEST_MESSAGE = "has requested to team with you! Accept with /team accept";
	
	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent e) {
		String message = e.message.getUnformattedText();
		if(message.contains(TEAM_REQUEST_MESSAGE)) {
			e.setCanceled(true);
			String[] split = message.split(" ");
			new ChatMessageComposer(split[0], EnumChatFormatting.YELLOW).appendMessage(new ChatMessageComposer(" has requested to team with you! Accept with ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("/team accept " + split[0] + " ", EnumChatFormatting.YELLOW).appendMessage(new ChatMessageComposer("Or alternatively click here", EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/team accept " + split[0], new ChatMessageComposer("Click here to accept " + split[0] + "'s team request!", EnumChatFormatting.AQUA))))).send(false);
		}
	}
	
}
