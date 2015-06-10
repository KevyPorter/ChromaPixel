package net.kevyporter.chromapixel.dmgreduction;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class DmgReductionCalc {

	public static List<String> armorReduct = new ArrayList<String>();
	
	public static void getReduction() {
		armorReduct.clear();
		PlayerArmorInfo.getValues();
		double armor = PlayerArmorInfo.helmet + PlayerArmorInfo.chest + PlayerArmorInfo.pants + PlayerArmorInfo.boots;
		double epf = calcArmorEpf();
		double avgdef = addArmorProtResistance(armor, calcProtection(epf), PlayerArmorInfo.resistance);
		double mindef = addArmorProtResistance(armor, Math.ceil(epf / 2.0D), PlayerArmorInfo.resistance);
		double maxdef = addArmorProtResistance(armor, Math.ceil(epf < 20.0D ? epf : 20.0D), PlayerArmorInfo.resistance);
		double min = roundDouble(mindef * 100.0D);
		double max = roundDouble(maxdef * 100.0D);
		double avg = roundDouble(avgdef * 100.0D);
		armorReduct.add(0, min + "");
		armorReduct.add(1, max + ""); 
		armorReduct.add(2, avg + ""); 
	}

	private static double addArmorProtResistance(double armor, double prot, int resi) {
		double protTotal = armor + (1.0D - armor) * prot * 0.04D;
		protTotal += (1.0D - protTotal) * resi * 0.2D;
		return protTotal < 1.0D ? protTotal : 1.0D;
	}

	private static double calcProtection(double armorEpf) {
		double protection = 0.0D;
		for (int i = 50; i <= 100; i++) {
			protection += (Math.ceil(armorEpf * i / 100.0D) < 20.0D ? Math.ceil(armorEpf * i / 100.0D) : 20.0D);
		}
		return protection / 51.0D;
	}

	private static double calcArmorEpf() {
		double prot = calcEpf(PlayerArmorInfo.helmetProt) + calcEpf(PlayerArmorInfo.chestProt) + calcEpf(PlayerArmorInfo.pantsProt) + calcEpf(PlayerArmorInfo.bootsProt);
		return prot < 25.0D ? prot : 25.0D;
	}

	private static double calcEpf(int prot) {
		return prot != 0 ? Math.floor((6.0D + Math.pow(prot, 2.0D)) * 0.25D) : 0.0D;
	}

	private static double roundDouble(double number) {
		double x = Math.round(number * 10000.0D);
		return x / 10000.0D;
	}
}
