package net.kevyporter.chromapixel.dmgreduction;

import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import net.kevyporter.chromapixel.chromahuds.InfoHUD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumChatFormatting;

public class PlayerArmorInfo {

	private static Minecraft mc = Minecraft.getMinecraft();
	private static EntityClientPlayerMP player = mc.thePlayer;

	public static double getHelmet() {
		if(player.inventory.armorItemInSlot(3) != null) {
			switch(Item.getIdFromItem(player.inventory.armorItemInSlot(3).getItem())) {
			case 298:
				return 0.04;
			case 314:
				return 0.08;
			case 302:
				return 0.08;
			case 306:
				return 0.08;
			case 310:
				return 0.12;
			}
		}
		return 0.0;
	}

	public static double getChestplate() {
		if(player.inventory.armorItemInSlot(2) != null) {
			switch(Item.getIdFromItem(player.inventory.armorItemInSlot(2).getItem())) {
			case 299:
				return 0.12;
			case 315:
				return 0.20;
			case 303:
				return 0.20;
			case 307:
				return 0.24;
			case 311:
				return 0.32;
			}
		}
		return 0.0;
	}

	public static double getPants() {
		if(player.inventory.armorItemInSlot(1) != null) {
			switch(Item.getIdFromItem(player.inventory.armorItemInSlot(1).getItem())) {
			case 300:
				return 0.08;
			case 316:
				return 0.12;
			case 304:
				return 0.16;
			case 308:
				return 0.20;
			case 312:
				return 0.24;
			}
		}
		return 0.0;
	}

	public static double getBoots() {
		if(player.inventory.armorItemInSlot(0) != null) {
			switch(Item.getIdFromItem(player.inventory.armorItemInSlot(0).getItem())) {
			case 301:
				return 0.04;
			case 317:
				return 0.04;
			case 305:
				return 0.04;
			case 309:
				return 0.08;
			case 313:
				return 0.12;
			}
		}
		return 0.0;
	}

	public static int getResistance() {
		if(player.isPotionActive(Potion.resistance)){
			Collection potionEffects = mc.thePlayer.getActivePotionEffects();
			Iterator it = potionEffects.iterator();
			while (it.hasNext())
			{
				PotionEffect potionEffect = (PotionEffect)it.next();
				Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
				if(potion.getName().equalsIgnoreCase(Potion.resistance.getName())) {
					return potionEffect.getAmplifier() + 1;
				}
			}
		}
		return 0;
	}

	public static int getHelmetProtection() {
		if(player.inventory.armorItemInSlot(3) != null) {
			return EnchantmentHelper.getEnchantmentLevel(0, player.inventory.armorItemInSlot(3));
		} 
		return 0;
	}

	public static int getChestplateProtection() {
		if(player.inventory.armorItemInSlot(2) != null) {
			return EnchantmentHelper.getEnchantmentLevel(0, player.inventory.armorItemInSlot(2));
		} 
		return 0;
	}

	public static int getPantsProtection() {
		if(player.inventory.armorItemInSlot(1) != null) {
			return EnchantmentHelper.getEnchantmentLevel(0, player.inventory.armorItemInSlot(1));
		} 
		return 0;
	}

	public static int getBootsProtection() {
		if(player.inventory.armorItemInSlot(0) != null) {
			return EnchantmentHelper.getEnchantmentLevel(0, player.inventory.armorItemInSlot(0));
		} 
		return 0;
	}

}
