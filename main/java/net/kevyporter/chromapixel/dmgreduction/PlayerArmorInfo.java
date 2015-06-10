package net.kevyporter.chromapixel.dmgreduction;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;

public class PlayerArmorInfo {

	private static Minecraft mc = Minecraft.getMinecraft();

	public static double helmet = 0.0;
	public static double chest = 0.0;
	public static double pants = 0.0;
	public static double boots = 0.0;

	public static int helmetProt = 0;
	public static int chestProt = 0;
	public static int pantsProt = 0;
	public static int bootsProt = 0;

	public static int resistance = 0;

	public static void getValues() {
		helmet = 0.0;
		chest = 0.0;
		pants = 0.0;
		boots = 0.0;
		helmetProt = 0;
		chestProt = 0;
		pantsProt = 0;
		bootsProt = 0;
		resistance = 0;

		if(mc.thePlayer.inventory.armorInventory[3] != null) {
			getHelmet();
			getHelmetProtection();
		} else {
			helmet = 0.0;
			helmetProt = 0;
		}
		if(mc.thePlayer.inventory.armorInventory[2] != null) {
			getChestplate();
			getChestplateProtection();
		} else {
			chest = 0.0;
			chestProt = 0;
		}
		if(mc.thePlayer.inventory.armorInventory[1] != null) {
			getPants();
			getPantsProtection();
		} else {
			pants = 0.0;
			pantsProt = 0;
		}
		if(mc.thePlayer.inventory.armorInventory[0] != null) {
			getBoots();
			getBootsProtection();
		} else {
			boots = 0.0;
			bootsProt = 0;
		}
		if(mc.thePlayer.getActivePotionEffects() != null) {
			getResistance();
		} else {
			resistance = 0;
		}
	}

	private static void getHelmet() {
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(3).getItem()) == 298) {
			helmet = 0.04;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(3).getItem()) == 314) {
			helmet = 0.08;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(3).getItem()) == 302) {
			helmet = 0.08;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(3).getItem()) == 306) {
			helmet = 0.08;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(3).getItem()) == 310) {
			helmet = 0.12;
		}
	}

	private static void getChestplate() {
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(2).getItem()) == 299) {
			chest = 0.12;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(2).getItem()) == 315) {
			chest = 0.20;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(2).getItem()) == 303) {
			chest = 0.20;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(2).getItem()) == 307) {
			chest = 0.24;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(2).getItem()) == 311) {
			chest = 0.32;
		}
	}

	private static void getPants() {
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(1).getItem()) == 300) {
			pants = 0.08;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(1).getItem()) == 316) {
			pants = 0.12;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(1).getItem()) == 304) {
			pants = 0.16;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(1).getItem()) == 308) {
			pants = 0.20;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(1).getItem()) == 312) {
			pants = 0.24;
		}
	}

	private static void getBoots() {
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(0).getItem()) == 301) {
			boots = 0.04;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(0).getItem()) == 317) {
			boots = 0.04;
		}	
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(0).getItem()) == 305) {
			boots = 0.04;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(0).getItem()) == 309) {
			boots = 0.08;
		}
		if(Item.getIdFromItem(mc.thePlayer.inventory.armorItemInSlot(0).getItem()) == 313) {
			boots = 0.12;
		}
	}

	private static void getResistance() {
		if(mc.thePlayer.isPotionActive(Potion.resistance)){
			Collection potionEffects = mc.thePlayer.getActivePotionEffects();
			Iterator it = potionEffects.iterator();
			while (it.hasNext())
			{
				PotionEffect potionEffect = (PotionEffect)it.next();
				Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
				if(potion.getName().equalsIgnoreCase(Potion.resistance.getName())) {
					resistance = potionEffect.getAmplifier() + 1;
				}
			}
		} else {
			resistance = 0;
		}
	}

	private static void getHelmetProtection() {
		if(helmet != 0) {
			helmetProt = EnchantmentHelper.getEnchantmentLevel(0, mc.thePlayer.inventory.armorItemInSlot(3));
		} else {
			helmetProt = 0;
		}
	}

	private static void getChestplateProtection() {
		if(chest != 0) {
			chestProt = EnchantmentHelper.getEnchantmentLevel(0, mc.thePlayer.inventory.armorItemInSlot(2));
		} else {
			chestProt = 0;
		}
	}

	private static void getPantsProtection() {
		if(pants != 0) {
			pantsProt = EnchantmentHelper.getEnchantmentLevel(0, mc.thePlayer.inventory.armorItemInSlot(1));
		} else {
			pantsProt = 0;
		}
	}

	private static void getBootsProtection() {
		if(boots != 0) {
			bootsProt = EnchantmentHelper.getEnchantmentLevel(0, mc.thePlayer.inventory.armorItemInSlot(0));
		} else {
			bootsProt = 0;
		}
	}

}
