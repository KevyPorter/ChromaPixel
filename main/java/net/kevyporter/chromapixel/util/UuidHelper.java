package net.kevyporter.chromapixel.util;

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
}
