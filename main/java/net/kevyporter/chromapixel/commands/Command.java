package net.kevyporter.chromapixel.commands;

import net.kevyporter.chromapixel.stats.ArcadeStatsDisplayer;
import net.kevyporter.chromapixel.stats.ArenaStatsDisplayer;
import net.kevyporter.chromapixel.stats.BlitzStatsDisplayer;
import net.kevyporter.chromapixel.stats.CopsAndCrimsStatsDisplayer;
import net.kevyporter.chromapixel.stats.MegaWallsStatsDisplayer;
import net.kevyporter.chromapixel.stats.PaintballStatsDisplayer;
import net.kevyporter.chromapixel.stats.QuakeStatsDisplayer;
import net.kevyporter.chromapixel.stats.TNTGamesStatsDisplayer;
import net.kevyporter.chromapixel.stats.UhcStatsDisplayer;
import net.kevyporter.chromapixel.stats.VampireZStatsDisplayer;
import net.kevyporter.chromapixel.stats.WallsStatsDisplayer;
import net.kevyporter.chromapixel.stats.WarlordsStatsDisplayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class Command extends CommandBase{

	private boolean isOp(ICommandSender sender)
	{
		return (MinecraftServer.getServer().isSinglePlayer()) 
				|| (!(sender instanceof EntityPlayerMP))
				|| MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayerMP)sender).getGameProfile());
	}

	public String getCommandName()
	{
		return "akjwdb";
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
		return "/akjwdb <gamemode> <player>";
	}

	public void processCommand(ICommandSender sender, String[] args)
	{
		try
		{
			if (args.length == 2) {
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("BSG")) {
						new BlitzStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("UHC")) {
						new UhcStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("MW")) {
						new MegaWallsStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("W")) {
						new WallsStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("AR")) {
						new ArenaStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("AC")) {
						new ArcadeStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("PB")) {
						new PaintballStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("CAC")) {
						new CopsAndCrimsStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("TNT")) {
						new TNTGamesStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("VZ")) {
						new VampireZStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("Q")) {
						new QuakeStatsDisplayer(args[1]).display();
					} else if (args[0].equalsIgnoreCase("WAR")) {
						new WarlordsStatsDisplayer(args[1]).display();
					}
				}
			}
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

}
