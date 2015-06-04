package net.kevyporter.chromapixel;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class ChromaPixelConfigGui extends GuiConfig {
	
	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		Configuration configFile = ChromaPixelMod.instance().CONFIG.getConfig();
		list.addAll(new ConfigElement(configFile.getCategory(ChromaPixelConfig.CHROMAPIXEL_CATEGORY)).getChildElements());
		return list;
	}
	
	public ChromaPixelConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), ChromaPixelMod.MODID, false, false, "ChromaPixel Config");
	}

}
