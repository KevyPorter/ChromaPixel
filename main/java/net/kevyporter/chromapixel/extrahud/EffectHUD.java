package net.kevyporter.chromapixel.extrahud;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class EffectHUD {

	public static boolean isEnabled = true;

	private static ResourceLocation inventoryResourceLocation = new ResourceLocation("textures/gui/container/inventory.png");

	public static boolean usePotionColors = true;
	public static float potionScale = 1.0F;

	private static Minecraft mc = Minecraft.getMinecraft();

	public static void render()
	{
		if ((!mc.gameSettings.showDebugInfo) && (mc.inGameHasFocus) && (!(mc.currentScreen instanceof GuiChat)) && (isEnabled)/* && (Minecraft.getMinecraft().func_147104_D() != null)*/) {
			Collection potionEffects = mc.thePlayer.getActivePotionEffects();
			Iterator it = potionEffects.iterator();

			ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

			int x = res.getScaledWidth() - 60;
			int y = res.getScaledHeight() - 15;

			x /= potionScale;
			y /= potionScale;
			GL11.glScalef(potionScale, potionScale, potionScale);

			mc.fontRenderer.drawString(EnumChatFormatting.GRAY + "[" + InfoHUD.mainColor + "Status HUD" + EnumChatFormatting.GRAY + "]", res.getScaledWidth() - mc.fontRenderer.getStringWidth(EnumChatFormatting.GRAY + "[" + InfoHUD.mainColor + "Status HUD" + EnumChatFormatting.GRAY + "]"), y - (10 * potionEffects.size()), 0xffffff);

			int i = 0;
			while (it.hasNext())
			{
				PotionEffect potionEffect = (PotionEffect)it.next();
				Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
				Boolean isFromBeacon = potionEffect.getIsAmbient();

				if (!isFromBeacon)
				{
						drawPotionIcon(x, y, potion);
						drawPotionDuration(x+10, y, potion, potionEffect);
						y -= 10;
						i++;
				}
			}

			GL11.glScalef(1f/potionScale, 1f/potionScale, 1f/potionScale);
		}
	}

	private static void drawPotionDuration(int x, int y, Potion potion, PotionEffect potionEffect)
	{
		String durationString = Potion.getDurationString(potionEffect);
		int potionDuration = potionEffect.getDuration();
		int colorInt = 0xFFFFFF;

		if(usePotionColors)
			colorInt = potion.getLiquidColor();

		String potionAmp = "I";
		if (potionEffect.getAmplifier() == 1)
		{
			potionAmp = "II";
		}
		else if (potionEffect.getAmplifier() == 2)
		{
			potionAmp = "III";
		}
		else if (potionEffect.getAmplifier() == 3)
		{
			potionAmp = "IV";
		}
		else if (potionEffect.getAmplifier() == 4)
		{
			potionAmp = "V";
		}
		else if (potionEffect.getAmplifier() == 5)
		{
			potionAmp = "VI";
		}
		else if (potionEffect.getAmplifier() == 6)
		{
			potionAmp = "VII";
		}
		else if (potionEffect.getAmplifier() == 7)
		{
			potionAmp = "VIII";
		}
		else if (potionEffect.getAmplifier() == 8)
		{
			potionAmp = "IX";
		}
		else if (potionEffect.getAmplifier() == 9)
		{
			potionAmp = "X";
		}
		else if (potionEffect.getAmplifier() > 9) {
			potionAmp = "" + (potionEffect.getAmplifier() + 1);        
		}

		
		mc.fontRenderer.drawString(potionAmp + " " + durationString, x, y, colorInt);
	}

	private static void drawPotionIcon(int x, int y, Potion potion)
	{
		mc.getTextureManager().bindTexture(inventoryResourceLocation);

		if(potion.hasStatusIcon())
		{
			int iconIndex = potion.getStatusIconIndex();
			int u = iconIndex % 8 * 18;
			int v = 198 + iconIndex / 8 * 18;
			int width = 18;
			int height = 18;
			float scaler = 0.5f;

			GL11.glColor4f(1f, 1f, 1f, 1f);

			renderCustomTexture(x, y, u, v, width, height, null, scaler);
		}
	}

	public static void renderCustomTexture(int x, int y, int u, int v, int width, int height, ResourceLocation resourceLocation, float scale)
	{
		x /= scale;
		y /= scale;

		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);

		if(resourceLocation != null)
			mc.getTextureManager().bindTexture(resourceLocation);

		mc.ingameGUI.drawTexturedModalRect(x, y, u, v, width, height);

		GL11.glPopMatrix();
	}

}
