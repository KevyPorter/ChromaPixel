package net.kevyporter.chromapixel.listeners;

import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ClickToTipListener {

	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent e) {
		String chat = e.message.getUnformattedText();
		if(chat.startsWith("Quakecraft - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
				chatp2 = "";
			}
			String click = chatp1.replace("Quakecraft - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Quakecraft " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Arcade - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Arcade - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Arcade " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Blitz Survival Games - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Blitz Survival Games - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Blitz Survival Games " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Walls - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Walls - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Walls " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Mega Walls - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Mega Walls - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Mega Walls " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Paintball - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Paintball - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Paintball " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Arena Brawl - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Arena Brawl - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Arena Brawl " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("TNTGames - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("TNTGames - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "TNTGames " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("VampireZ - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("VampireZ - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "VampireZ " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Cops and Crims - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Cops and Crims - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Cops and Crims " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("UHC Champions - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("UHC Champions - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "UHC Champions " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Warlords - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Warlords - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Warlords " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
		if(chat.startsWith("Turbo Kart Racers - Triple Coins from ")) {
			e.setCanceled(true);
			String chatp1 = "";
			String chatp2 = "";
			if(chat.contains(",")) {
				chatp1 = chat.substring(0, chat.indexOf(","));
				chatp2 = chat.substring(chat.indexOf(","), chat.length());
			} else {
				chatp1 = chat;	
			}
			String click = chatp1.replace("Turbo Kart Racers - Triple Coins from ", "");
			new ChatMessageComposer(EnumChatFormatting.AQUA + "Turbo Kart Racers " + EnumChatFormatting.YELLOW + "- " + EnumChatFormatting.GREEN + "Triple Coins from ").appendMessage(new ChatMessageComposer(click, EnumChatFormatting.GREEN).makeClickable(Action.RUN_COMMAND, "/tip " + click, new ChatMessageComposer(EnumChatFormatting.GRAY + "Click to tip " + EnumChatFormatting.BLUE + click)).appendMessage(new ChatMessageComposer(chatp2, EnumChatFormatting.GREEN))).send(false);
		}
	}

}
