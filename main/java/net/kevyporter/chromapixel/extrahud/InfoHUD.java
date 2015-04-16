package net.kevyporter.chromapixel.extrahud;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.kevyporter.chromapixel.ChromaPixelMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.client.FMLClientHandler;

public class InfoHUD {

	private static List<String> display = new ArrayList();
	public static Boolean isEnabled = true;
	public static Boolean showCoords = true;
	public static EnumChatFormatting mainColor = EnumChatFormatting.YELLOW;
	public static EnumChatFormatting itemColor = EnumChatFormatting.GOLD;

	public static void renderDisplay() {
		if (isEnabled) {
			display = getInfoDisplay();
			Minecraft mc = FMLClientHandler.instance().getClient();
			ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			int w = 1;
			int h = 15;
			if ((!mc.gameSettings.showDebugInfo) && (mc.inGameHasFocus) && (!(mc.currentScreen instanceof GuiChat)) && (isEnabled) && (Minecraft.getMinecraft().func_147104_D() != null)) {
				FontRenderer fontRenderer = FMLClientHandler.instance().getClient().fontRenderer;
				for (int i = 0; i < display.size(); i++) {
					if ((display.get(i) != null) && (!((String)display.get(i)).isEmpty())) {
						fontRenderer.drawString((String)display.get(i), w, h, 0xffffff);
						h += 10;
					}
				}
			}
		}
	}

	private static List getInfoDisplay() {
		display.clear();
		display.add(EnumChatFormatting.GRAY + "[" + mainColor + "FPS" + EnumChatFormatting.GRAY + "] " + itemColor + getFPS());
		if(showCoords) {
			display.add(EnumChatFormatting.GRAY + "[" + mainColor + "X" + EnumChatFormatting.GRAY + "] " + itemColor + getCoords(0));
			display.add(EnumChatFormatting.GRAY + "[" + mainColor + "Y" + EnumChatFormatting.GRAY + "] " + itemColor + getCoords(1));
			display.add(EnumChatFormatting.GRAY + "[" + mainColor + "Z" + EnumChatFormatting.GRAY + "] " + itemColor + getCoords(2));
			display.add(EnumChatFormatting.GRAY + "[" + mainColor + "F" + EnumChatFormatting.GRAY + "] " + itemColor + getCoords(3) + EnumChatFormatting.GRAY + " [" + mainColor + getCoords(4) + EnumChatFormatting.GRAY + "]");
		}
		display.add(EnumChatFormatting.GRAY + "[" + mainColor + "TIME" + EnumChatFormatting.GRAY + "] " + itemColor + getTime());
		display.add(EnumChatFormatting.GRAY + "[" + mainColor + "IP" + EnumChatFormatting.GRAY + "] " + itemColor + getIP());
		display.add(EnumChatFormatting.GRAY + "[" + mainColor + "PLAYERS" + EnumChatFormatting.GRAY + "] " + itemColor + getPlayers());
		if(getPing() >= 0 && getPing() <= 99) {
			display.add(EnumChatFormatting.GRAY + "[" + mainColor + "PING" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.GREEN + getPing());
		} else
			if(getPing() >= 100 && getPing() <= 199) {
				display.add(EnumChatFormatting.GRAY + "[" + mainColor + "PING" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.YELLOW + getPing());
			} else
				if(getPing() >= 200 && getPing() <= 299) {
					display.add(EnumChatFormatting.GRAY + "[" + mainColor + "PING" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.GOLD + getPing());
				} else
					if(getPing() >= 300 && getPing() <= 399) {
						display.add(EnumChatFormatting.GRAY + "[" + mainColor + "PING" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RED + getPing());
					} else {
						display.add(EnumChatFormatting.GRAY + "[" + mainColor + "PING" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.DARK_RED + getPing());
					}
		display.add(EnumChatFormatting.GRAY + "[" + mainColor + "FACING" + EnumChatFormatting.GRAY + "] " + itemColor + compass());
		return display;
	}

	private static String getFPS() {
		String fps = "0";
		try{
			fps = Minecraft.getMinecraft().debug.substring(0, Minecraft.getMinecraft().debug.indexOf(' '));
		}catch(Exception e){}
		return fps;
	}

	private static String getIP() {
		String ip = "";
		if (Minecraft.getMinecraft().func_147104_D().serverIP != null) { 
			ip = "" + Minecraft.getMinecraft().func_147104_D().serverIP;
		} else {
			ip = "null";
		}
		return ip;
	}

	private static int getPing() {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		if (player == null) {
			return -1;
		}
		NetHandlerPlayClient sendQueue = player.sendQueue;
		if (sendQueue == null) {
			return -2;
		}
		List<GuiPlayerInfo> playerList = sendQueue.playerInfoList;
		for (GuiPlayerInfo playerInfo : playerList) {
			if (playerInfo.name.equals(Minecraft.getMinecraft().thePlayer.getDisplayName())) {
				return playerInfo.responseTime;
			}
		}
		return -3;
	}

	private static int getPlayers() {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		if (player == null) {
			return -1;
		}
		NetHandlerPlayClient sendQueue = player.sendQueue;
		if (sendQueue == null) {
			return -2;
		}
		List<GuiPlayerInfo> playerList = sendQueue.playerInfoList;
		if(playerList.size() > 0) {
			return playerList.size();
		}
		return -3;
	}

	private static String getTime() {
		return new SimpleDateFormat("h:mm a").format(Calendar.getInstance().getTime());
	}

	private static String compass() {
		if (Minecraft.getMinecraft().thePlayer != null) {
			float yaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
			yaw %= 360;
			if (yaw < 0) {
				yaw += 360;
			}
			yaw = yaw / 90;
			DecimalFormat df = new DecimalFormat("#.#");
			Double m;
			String direction;
			m = Double.valueOf(df.format(yaw));
			if (m <= 0.2) {
				direction = "S";
			} else if (m >= 0.3 && m <= 0.7) {
				direction = "SW";
			} else if (m >= 0.8 && m <= 1.2) {
				direction = "W";
			} else if (m >= 1.3 && m <= 1.7) {
				direction = "NW";
			} else if (m >= 1.8 && m <= 2.2) {
				direction = "N";
			} else if (m >= 2.3 && m <= 2.7) {
				direction = "NE";
			} else if (m >= 2.8 && m <= 3.2) {
				direction = "E";
			} else if (m >= 3.3 && m <= 3.7){
				direction = "SE";
			} else {
				direction = "S";
			}
			return direction;
		}
		return "null";
	}

	private static String getCoords(int i) {
		List<String> coords = new ArrayList<String>();
		Double x,y,z,m;
		float yaw;
		String f;
		DecimalFormat df = new DecimalFormat("#.#");
		if(Minecraft.getMinecraft().thePlayer != null) {
			x = Minecraft.getMinecraft().thePlayer.posX;
			y = Minecraft.getMinecraft().thePlayer.posY;
			z = Minecraft.getMinecraft().thePlayer.posZ;
			yaw = Minecraft.getMinecraft().thePlayer.rotationYaw;
			yaw %= 360;
			if (yaw < 0) {
				yaw += 360;
			}
			coords.clear();
			yaw = yaw / 90;
			m = Double.valueOf(df.format(yaw));
			if (m <= 0.2) {
				f = "Z+";
			} else if (m >= 0.3 && m <= 0.7) {
				f = "X-, Z+";
			} else if (m >= 0.8 && m <= 1.2) {
				f = "X-";
			} else if (m >= 1.3 && m <= 1.7) {
				f = "X-, Z-";
			} else if (m >= 1.8 && m <= 2.2) {
				f = "Z-";
			} else if (m >= 2.3 && m <= 2.7) {
				f = "X+, Z-";
			} else if (m >= 2.8 && m <= 3.2) {
				f = "X+";
			} else if (m >= 3.3 && m <= 3.7){
				f = "X+, Z+";
			} else {
				f = "Z+";
			}
			coords.add(0, df.format(x));
			coords.add(1, df.format(y));
			coords.add(2, df.format(z));
			coords.add(3, "" + m);
			coords.add(4, "" + f);
			return coords.get(i);
		}
		return "null";
	}

}
