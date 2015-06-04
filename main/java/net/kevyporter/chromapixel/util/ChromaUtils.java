package net.kevyporter.chromapixel.util;

import net.kevyporter.chromapixel.commands.AfkCommand;
import net.kevyporter.chromapixel.commands.GetStatsCommand;
import net.kevyporter.chromapixel.commands.NameViewCommand;
import net.kevyporter.chromapixel.commands.StatsCommand;
import net.kevyporter.chromapixel.commands.ToggleChatCommand;
import net.kevyporter.chromapixel.listeners.AfkListener;
import net.kevyporter.chromapixel.listeners.ClickToTipListener;
import net.kevyporter.chromapixel.listeners.TeamAcceptListener;
import net.kevyporter.chromapixel.listeners.ToggleChatListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

public class ChromaUtils {

	public static void register() {
		MinecraftForge.EVENT_BUS.register(new ToggleChatListener());
		MinecraftForge.EVENT_BUS.register(new AfkListener());
		MinecraftForge.EVENT_BUS.register(new TeamAcceptListener());
		MinecraftForge.EVENT_BUS.register(new ClickToTipListener());
		
		ClientCommandHandler.instance.registerCommand(new AfkCommand());
		ClientCommandHandler.instance.registerCommand(new GetStatsCommand());
		ClientCommandHandler.instance.registerCommand(new NameViewCommand());
		ClientCommandHandler.instance.registerCommand(new StatsCommand());
		ClientCommandHandler.instance.registerCommand(new ToggleChatCommand());
	}
	
}
