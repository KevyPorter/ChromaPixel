package net.kevyporter.chromapixel.extrahud;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.kevyporter.chromapixel.ChromaPixelMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ArmorHUD {

	private static List<ItemStack> inv = new ArrayList<ItemStack>();

	public static boolean isEnabled = true;
	
	public static void render() {
		if ((!Minecraft.getMinecraft().gameSettings.showDebugInfo) && (Minecraft.getMinecraft().inGameHasFocus) && (!(Minecraft.getMinecraft().currentScreen instanceof GuiChat)) && (isEnabled)) {
			ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderHelper.enableStandardItemLighting();
			RenderHelper.enableGUIStandardItemLighting();
			RenderItem itemRenderer = new RenderItem();
			int h = 15;
			getInventory();
			for(int i = 0; i < inv.size(); i++) {
				itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(), inv.get(i), res.getScaledWidth() - 20, h);
				h += 16;
			}
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(32826);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	private static List<ItemStack> getInventory() {
		inv.clear();
		if(Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null) {
			inv.add(0, Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem());
		}
		if(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3) != null) {
			inv.add(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3));
		}
		if(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(2) != null) {
			inv.add(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(2));
		}
		if(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(1) != null) {
			inv.add(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(1));
		}
		if(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(0) != null) {
			inv.add(Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(0));
		}
		return inv;
	}

}
