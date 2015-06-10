package net.kevyporter.chromapixel;

import java.io.File;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;

public class ChromaPixelConfig {

	private Configuration config;
	
	public static final String CHROMAPIXEL_CATEGORY = "chromapixel";
	
	public static boolean useAPI = true;
	public static boolean showLogo;
	public static boolean showFPS;
	public static boolean showAdvCoords;
	public static boolean showTime;
	public static boolean showIP;
	public static boolean showPlayers;
	public static boolean showPing;
	public static boolean showColoredPing;
	public static boolean showFacing;
	public static boolean showInfoHUD;
	public static boolean showArmorHUD;
	public static boolean showEffectHUD;
	public static String whereInfoHUD;
	public static String whereArmorHUD;
	public static String whereEffectHUD;
	public static String mainColor;
	public static String itemColor;
	public static boolean usePotionColors;
	
	public ChromaPixelConfig(File config) {
		this.config = new Configuration(config, "1");
		this.config.load();
	}
	
	String[] hudValues = {"top_left", "top_right", "middle_left", "middle_right", "bottom_left", "bottom_right"};
	String[] colorValues = {EnumChatFormatting.AQUA + "Aqua", EnumChatFormatting.BLACK + "Black", EnumChatFormatting.BLUE + "Blue", EnumChatFormatting.DARK_AQUA + "Dark_Aqua", EnumChatFormatting.DARK_BLUE + "Dark_Blue", EnumChatFormatting.DARK_GRAY + "Dark_Gray", EnumChatFormatting.DARK_GREEN + "Dark_Green", EnumChatFormatting.DARK_PURPLE + "Dark_Purple", EnumChatFormatting.GOLD + "Gold", EnumChatFormatting.GRAY + "Gray", EnumChatFormatting.LIGHT_PURPLE + "Light_Purple", EnumChatFormatting.RED + "Red", EnumChatFormatting.WHITE + "White", EnumChatFormatting.YELLOW + "Yellow"};
	
	public void syncConfig() {
		showLogo = this.config.get(CHROMAPIXEL_CATEGORY, "showLogo", true, "Show the 'ChromaPixel' logo in the top left.").getBoolean(true);
		showFPS = this.config.get(CHROMAPIXEL_CATEGORY, "showFPS", true, "Shows the FPS in the InfoHUD.").getBoolean(true);
		showAdvCoords = this.config.get(CHROMAPIXEL_CATEGORY, "showAdvCoords", true, "Shows the Advanced Coords in the InfoHUD").getBoolean(true);
		showTime = this.config.get(CHROMAPIXEL_CATEGORY, "showTime", true, "Shows the Time in the InfoHUD").getBoolean(true);
		showIP = this.config.get(CHROMAPIXEL_CATEGORY, "showIP", true, "Shows the IP in the InfoHUD").getBoolean(true);
		showPlayers = this.config.get(CHROMAPIXEL_CATEGORY, "showPlayers", true, "Shows the Players in the InfoHUD").getBoolean(true);
		showPing = this.config.get(CHROMAPIXEL_CATEGORY, "showPing", true, "Shows the Ping in the InfoHUD").getBoolean(true);
		showColoredPing = this.config.get(CHROMAPIXEL_CATEGORY, "showColoredPing", true, "Changes the color of the ping when displaying it\n(Green = below 100\nYellow = below 200\nGold = below 300\nRed = below 400\nDark red anything above 400)").getBoolean(true);
		showFacing = this.config.get(CHROMAPIXEL_CATEGORY, "showFacing", true, "Shows the Facing in the InfoHUD").getBoolean(true);
		showInfoHUD = this.config.get(CHROMAPIXEL_CATEGORY, "showInfoHUD", true, "Shows the InfoHUD").getBoolean(true);
		showArmorHUD = this.config.get(CHROMAPIXEL_CATEGORY, "showArmorHUD", true, "Shows ArmorHUD").getBoolean(true);
		showEffectHUD = this.config.get(CHROMAPIXEL_CATEGORY, "showEffectHUD", true, "Shows EffectHUD").getBoolean(true);
		usePotionColors = this.config.get(CHROMAPIXEL_CATEGORY, "usePotionColors", true, "Use the color of the potion for the potion hud display.").getBoolean(true);
		whereInfoHUD = this.config.getString("whereInfoHUD", CHROMAPIXEL_CATEGORY, EnumChatFormatting.RED + "Currently Disabled!", "Choose where to display the Info HUD.\nCurrently Disabled.", new String[] {EnumChatFormatting.RED + "Currently Disabled!"});
		whereArmorHUD = this.config.getString("whereArmorHUD", CHROMAPIXEL_CATEGORY, EnumChatFormatting.RED + "Currently Disabled!", "Choose where to display the Armor HUD.\nCurrently Disabled.", new String[] {EnumChatFormatting.RED + "Currently Disabled!"});
		whereEffectHUD = this.config.getString("whereEffectHUD", CHROMAPIXEL_CATEGORY, EnumChatFormatting.RED + "Currently Disabled!", "Choose where to display the Effect HUD.\nCurrently Disabled.", new String[] {EnumChatFormatting.RED + "Currently Disabled!"});
		mainColor = this.config.getString("mainColor", CHROMAPIXEL_CATEGORY, EnumChatFormatting.GOLD + "Gold", "Choose the main color of the HUD.\nCurrently Disabled.", colorValues);
		itemColor = this.config.getString("itemColor", CHROMAPIXEL_CATEGORY, EnumChatFormatting.YELLOW + "Yellow", "Choose the item color of the HUD.\nCurrently Disabled.", colorValues);
		
		if(this.config.hasChanged()) {
			this.config.save();
		}
	}
	
	public Configuration getConfig() {
		return config;
	}
	
}