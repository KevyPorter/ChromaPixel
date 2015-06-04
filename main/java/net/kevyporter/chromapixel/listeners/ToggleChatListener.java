package net.kevyporter.chromapixel.listeners;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ToggleChatListener
{
	public static Boolean guildChat = true;
	public static Boolean partyChat = true;
	public static Boolean pmsChat = true;
	public static Boolean shoutsChat = true;

	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent e)
	{
		String msg = e.message.getUnformattedText();
		if (!partyChat) {
			if (msg.startsWith("Party >")) {
				e.setCanceled(true);
			}
		}
		if (!guildChat) {
			if (msg.startsWith("Guild >")) {
				e.setCanceled(true);
			}
		}
		if (!pmsChat) {
			if (msg.startsWith("From")) {
				e.setCanceled(true);
			}
		}
		if(!shoutsChat) {
			if (msg.startsWith("[SHOUT]")) {
				e.setCanceled(true);
			}
		}
	}
}


