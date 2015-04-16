package net.kevyporter.chromapixel;

import net.kevyporter.chromapixel.api.interaction.Queue;
import net.kevyporter.chromapixel.extrahud.ArmorHUD;
import net.kevyporter.chromapixel.extrahud.EffectHUD;
import net.kevyporter.chromapixel.extrahud.InfoHUD;
import net.kevyporter.chromapixel.listeners.AutoLobbyCommand;
import net.kevyporter.chromapixel.util.ChromaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

@Mod(modid = ChromaPixelMod.MODID, version = ChromaPixelMod.VERSION, name = ChromaPixelMod.NAME)
public class ChromaPixelMod
{
	public static final String MODID = "chromapixelmod";
	public static final String NAME = "ChromaPixel";
	public static final String VERSION = "0.7.0";
	public static final boolean IS_DEBUGGING = false;
	public static final String CHROMA_PIXEL = "" + EnumChatFormatting.GREEN + EnumChatFormatting.BOLD + "Chroma" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + "Pixel";

	private static ChromaPixelMod instance;

	public static boolean isUpdate = false;
	public Logger LOGGER;
	private boolean isUpdateMessageQueued;
	private Queue apiQueue;

	public static final String KEY_CATEGORY = "";
	private KeyBinding hideHUDKey;
	private KeyBinding hideArmorHUDKey;
	private KeyBinding hideEffectHUDKey;
	private KeyBinding hideCoordsKey;
	private KeyBinding hubKey;

	private AutoLobbyCommand lobbyCommandConfirmer;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		try {
			instance = this;
			this.LOGGER = LogManager.getLogger("ChromaPixel");
			this.apiQueue = new Queue();
			ChromaPixelUpdater.checkForUpdate();
		} catch(Exception e) {
			logWarn("An exception occured in preInit(). Stacktrace below.");
			e.printStackTrace();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);

		ChromaUtils.register();

		this.lobbyCommandConfirmer = new AutoLobbyCommand();

		this.hideHUDKey = new KeyBinding("Hide Info HUD", Keyboard.KEY_O, KEY_CATEGORY);
		this.hideCoordsKey = new KeyBinding("Hide HUD Coords", Keyboard.KEY_P, KEY_CATEGORY);
		this.hideArmorHUDKey = new KeyBinding("Hide Armor HUD", Keyboard.KEY_K, KEY_CATEGORY);
		this.hideEffectHUDKey = new KeyBinding("Hide Effect HUD", Keyboard.KEY_L, KEY_CATEGORY);
		this.hubKey = new KeyBinding("Hub", Keyboard.KEY_B, KEY_CATEGORY);
		ClientRegistry.registerKeyBinding(this.hideHUDKey);
		ClientRegistry.registerKeyBinding(this.hideCoordsKey);
		ClientRegistry.registerKeyBinding(this.hideArmorHUDKey);
		ClientRegistry.registerKeyBinding(this.hideEffectHUDKey);
		ClientRegistry.registerKeyBinding(this.hubKey);
	}

	@SubscribeEvent
	public void onChatMessage(ClientChatReceivedEvent event) {
		try {
			this.apiQueue.onChatMessage(event.message.getUnformattedText());

			this.lobbyCommandConfirmer.onChatReceived(event);

		} catch(Exception e) {
			logWarn("An exception occured in onChatMessage(). Stacktrace below.");
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event) {
		try {
			this.apiQueue.onClientTick();
		} catch(Exception e) {
			logWarn("An exception occured in onClientTick(). Stacktrace below.");
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		try {
			if(Minecraft.getMinecraft().func_147104_D() != null){

				if(isUpdate) {
					if ((!Minecraft.getMinecraft().gameSettings.showDebugInfo) && (Minecraft.getMinecraft().inGameHasFocus) && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat))) {
						ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
						FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
						String updateMessage = EnumChatFormatting.RED + "UPDATE Version: " + EnumChatFormatting.YELLOW + ChromaPixelUpdater.update;
						String forumLink = EnumChatFormatting.GOLD + "Download from here: " + EnumChatFormatting.GREEN + "http://bit.ly/TheChromaPixelMod";
						int y = res.getScaledHeight() / 2 - 10;
						int x = res.getScaledWidth() / 2 - (fontRenderer.getStringWidth(updateMessage) / 2);
						fontRenderer.drawString(updateMessage, x, y, 0xffffff, true);
						x = res.getScaledWidth() / 2 - (fontRenderer.getStringWidth(forumLink) / 2);
						fontRenderer.drawString(forumLink, x, y+10, 0xffffff, true);
					}
				}

				if ((!Minecraft.getMinecraft().gameSettings.showDebugInfo) && (Minecraft.getMinecraft().inGameHasFocus) && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat))) {
					if(InfoHUD.isEnabled || ArmorHUD.isEnabled || EffectHUD.isEnabled) {
						Minecraft.getMinecraft().fontRenderer.drawString(ChromaPixelMod.CHROMA_PIXEL + EnumChatFormatting.GOLD + " " + ChromaPixelMod.VERSION + EnumChatFormatting.BOLD + " BETA", 1, 1, 0xffffff);
					}
					InfoHUD.renderDisplay();
					ArmorHUD.render();
					// Color goes weird when rendering in the actual class :/
					if(ArmorHUD.isEnabled) { ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight); Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.GRAY + "[" + InfoHUD.mainColor + "Armor HUD" + EnumChatFormatting.GRAY + "]", res.getScaledWidth() - (Minecraft.getMinecraft().fontRenderer.getStringWidth(EnumChatFormatting.GRAY + "[" + InfoHUD.mainColor + "Armor HUD" + EnumChatFormatting.GRAY + "]") + 1), 1, 0xffffff); }
					EffectHUD.render();
				}
			}
		} catch(Exception e) {
			logWarn("An exception occured in onRenderTick(). Stacktrace below.");
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		try {
			if(this.hideHUDKey.isPressed()) {
				InfoHUD.isEnabled = !InfoHUD.isEnabled;
			}
			if(this.hideCoordsKey.isPressed()) {
				InfoHUD.showCoords = !InfoHUD.showCoords; 
			}
			if(this.hideArmorHUDKey.isPressed()) {
				ArmorHUD.isEnabled = !ArmorHUD.isEnabled;
			}
			if(this.hideEffectHUDKey.isPressed()) {
				EffectHUD.isEnabled = !EffectHUD.isEnabled;
			}
			if(this.hubKey.isPressed()) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/hub");
			}
		} catch(Exception e) {
			logWarn("An exception occured in onKeyInput(). Stacktrace below.");
			e.printStackTrace();
		}
	}

	public static ChromaPixelMod instance() {
		return instance;
	}

	public void logDebug(String s) {
		if(IS_DEBUGGING) {
			this.LOGGER.info("[DEBUG] "  + s);
		}
	}

	public void logInfo(String s) {
		this.LOGGER.info(s);
	}

	public void logWarn(String s) {
		this.LOGGER.warn(s);
	}

	public void logError(String s) {
		this.LOGGER.error(s); 	
	}
}
