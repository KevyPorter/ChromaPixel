package net.kevyporter.chromapixel.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.kevyporter.chromapixel.ChromaPixelMod;
import net.minecraft.client.Minecraft;

import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;

public class UuidHelper {

	static int counter;

	private UuidHelper() {}

	public static String getUsernameFormUUID(String uuid) {
		if(ChromaPixelMod.IS_DEBUGGING) {
			long time = System.currentTimeMillis();
			String name = Minecraft.getMinecraft().func_152347_ac().fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString(uuid), (String)null), false).getName();
			ChromaPixelMod.instance().logDebug("Took " + (System.currentTimeMillis() - time) + "ms to process UUID. Counter is at " + counter++);
			return name;
		} else {
			return Minecraft.getMinecraft().func_152347_ac().fillProfileProperties(new GameProfile(UUIDTypeAdapter.fromString(uuid), (String)null), false).getName();
		}
	}

	public static String uuid = "";
	public static String getUUIDFromUsername(final String username) {
		try {
			URL uuidgrabber = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(uuidgrabber.openStream()));
			String uuidfromweb;
			if ((uuidfromweb = br1.readLine()) != null) {
				uuid = uuidfromweb.substring(7, 39);
			}
			br1.close();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		return uuid;
	}
}
