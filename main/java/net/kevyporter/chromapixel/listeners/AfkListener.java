package net.kevyporter.chromapixel.listeners;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AfkListener
{
	public static Boolean isAfk = false;
	public static String afkMessage = "";
	public static ArrayList<String> afkMessages = new ArrayList<String>();

	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent e) {
		if (isAfk) {
			if (e.message.getUnformattedText().startsWith("From")) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/r " + afkMessage);
				afkMessages.add(e.message.getFormattedText());
			}
		}
	}
}


