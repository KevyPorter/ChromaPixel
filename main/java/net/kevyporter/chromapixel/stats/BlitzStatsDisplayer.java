package net.kevyporter.chromapixel.stats;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.kevyporter.net.hypixel.api.util.GameType;
import net.minecraft.util.EnumChatFormatting;

public class BlitzStatsDisplayer
extends StatsDisplayer
{
	public BlitzStatsDisplayer(String name)
	{
		super(name);
		getStats();
	}

	public void displayStatsInChat()
	{
		getStats();
		ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
		new ChatMessageComposer(this.playerName + "'s stats in " + GameType.SURVIVAL_GAMES.getName(), EnumChatFormatting.GREEN).send();

		new ChatMessageComposer("     Kills: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer(this.statistics.get("HungerGames").getAsJsonObject().get("kills").getAsString(), EnumChatFormatting.GOLD).appendMessage(new ChatMessageComposer(" Wins: ", EnumChatFormatting.GREEN)
		.appendMessage(new ChatMessageComposer(this.statistics.get("HungerGames").getAsJsonObject().get("wins").getAsString(), EnumChatFormatting.GOLD)))).send(false);

		new ChatMessageComposer("     Coins: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer(this.statistics.get("HungerGames").getAsJsonObject().get("coins").getAsString(), EnumChatFormatting.GOLD)).send(false);
		if(this.arach != 1) {
			new ChatMessageComposer("     Arachnologist: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.arach, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.archer != 1) {
			new ChatMessageComposer("     Archer: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.archer, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.armor != 1) {
			new ChatMessageComposer("     Armorer: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.armor, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.astro != 1) {
			new ChatMessageComposer("     Astronaut: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.astro, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.baker != 1) {
			new ChatMessageComposer("     Baker: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.baker, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.blaze != 1) {
			new ChatMessageComposer("     Blaze: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.blaze, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.creeper != 1) {
			new ChatMessageComposer("     Creepertamer: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.creeper, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.fish != 1) {
			new ChatMessageComposer("     Fisherman: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.fish, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.horse != 1) {
			new ChatMessageComposer("     Horsetamer: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.horse, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.hunter != 1) {
			new ChatMessageComposer("     Hunter: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.hunter, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.knight != 1) {
			new ChatMessageComposer("     Knight: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.knight, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.meat != 1) {
			new ChatMessageComposer("     Meatmaster: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.meat, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.necro != 1) {
			new ChatMessageComposer("     Necromancer: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.necro, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.pigman != 1) {
			new ChatMessageComposer("     Pigman: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.pigman, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.red != 1) {
			new ChatMessageComposer("     Reddragon: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.red, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.rogue != 1) {
			new ChatMessageComposer("     Rogue: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.rogue, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.scout != 1) {
			new ChatMessageComposer("     Scout: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.scout, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.slimey != 1) {
			new ChatMessageComposer("     SlimeySlime: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.slimey, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.snow != 1) {
			new ChatMessageComposer("     Snowman: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.snow, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.spel != 1) {
			new ChatMessageComposer("     Speleologist: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.spel, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.tim != 1) {
			new ChatMessageComposer("     Tim: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.tim, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.toxic != 1) {
			new ChatMessageComposer("     Toxicologist: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.toxic, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.troll != 1) {
			new ChatMessageComposer("     Troll: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.troll, EnumChatFormatting.GOLD)).send(false);
		}
		if(this.wolf != 1) {
			new ChatMessageComposer("     Wolftamer: ", EnumChatFormatting.GREEN).appendMessage(new ChatMessageComposer("" + this.wolf, EnumChatFormatting.GOLD)).send(false);
		}
		ChatMessageComposer.printSeparationMessage(EnumChatFormatting.YELLOW);
	}

	private int wolf = 1;
	private int troll = 1;
	private int toxic = 1;
	private int tim = 1;
	private int spel = 1;
	private int slimey = 1;
	private int snow = 1;
	private int scout = 1;
	private int rogue = 1;
	private int red = 1;
	private int pigman = 1;
	private int necro = 1;
	private int meat = 1;
	private int knight = 1;
	private int hunter = 1;
	private int horse = 1;
	private int fish = 1;
	private int creeper = 1;
	private int blaze = 1;
	private int baker = 1;
	private int astro = 1;
	private int armor = 1;
	private int archer = 1;
	private int arach = 1;

	private void getStats()
	{
		try
		{
			this.arach = (this.statistics.get("HungerGames").getAsJsonObject().get("arachnologist").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.archer = (this.statistics.get("HungerGames").getAsJsonObject().get("archer").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.armor = (this.statistics.get("HungerGames").getAsJsonObject().get("armorer").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.astro = (this.statistics.get("HungerGames").getAsJsonObject().get("astronaut").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.baker = (this.statistics.get("HungerGames").getAsJsonObject().get("baker").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.blaze = (this.statistics.get("HungerGames").getAsJsonObject().get("blaze").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.creeper = (this.statistics.get("HungerGames").getAsJsonObject().get("creepertamer").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.fish = (this.statistics.get("HungerGames").getAsJsonObject().get("fisherman").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.horse = (this.statistics.get("HungerGames").getAsJsonObject().get("horsetamer").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.hunter = (this.statistics.get("HungerGames").getAsJsonObject().get("hunter").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.knight = (this.statistics.get("HungerGames").getAsJsonObject().get("knight").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.meat = (this.statistics.get("HungerGames").getAsJsonObject().get("meatmaster").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.necro = (this.statistics.get("HungerGames").getAsJsonObject().get("necromancer").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.pigman = (this.statistics.get("HungerGames").getAsJsonObject().get("pigman").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.red = (this.statistics.get("HungerGames").getAsJsonObject().get("reddragon").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.rogue = (this.statistics.get("HungerGames").getAsJsonObject().get("rogue").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.scout = (this.statistics.get("HungerGames").getAsJsonObject().get("scout").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.slimey = (this.statistics.get("HungerGames").getAsJsonObject().get("slimeyslime").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.snow = (this.statistics.get("HungerGames").getAsJsonObject().get("snowman").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.spel = (this.statistics.get("HungerGames").getAsJsonObject().get("speleologist").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.tim = (this.statistics.get("HungerGames").getAsJsonObject().get("tim").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.toxic = (this.statistics.get("HungerGames").getAsJsonObject().get("toxicologist").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.troll = (this.statistics.get("HungerGames").getAsJsonObject().get("troll").getAsInt() + 1);
		}
		catch (Exception e) {}
		try
		{
			this.wolf = (this.statistics.get("HungerGames").getAsJsonObject().get("wolftamer").getAsInt() + 1);
		}
		catch (Exception e) {}
	}
}
