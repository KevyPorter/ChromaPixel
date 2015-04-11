package net.kevyporter.chromapixel.listeners;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class AutoLobbyCommand {
	public static final String LOBBY_MSG = "Are you sure? Type /lobby again if you really want to quit.";

	public AutoLobbyCommand() {

	}

	public void onChatReceived(ClientChatReceivedEvent event) {
		String message = event.message.getUnformattedText();
		if(message.equalsIgnoreCase(LOBBY_MSG)){
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/lobby");
			event.setCanceled(true);
		}
		return;
	}
}
