package net.kevyporter.chromapixel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ChromaPixelUpdater {

	public static String update;
	
	public static void checkForUpdate() throws IOException {
		try {
			String ver = "";
			String version = "";
			URL u = new URL("https://raw.githubusercontent.com/KevyPorter/ChromaPixel/master/Updater/update.txt");
			HttpsURLConnection con = (HttpsURLConnection)u.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String input;
			while ((input = br.readLine()) != null) {
				ver = ver + input;
			}
			br.close();
			update = ver;
			ver = ver.replace(".", "");
			version = ChromaPixelMod.VERSION.replace(".", "");
			int v1 = Integer.parseInt(ver);
			int v2 = Integer.parseInt(version);
			if(v1 > v2) {
				ChromaPixelMod.isUpdate = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}