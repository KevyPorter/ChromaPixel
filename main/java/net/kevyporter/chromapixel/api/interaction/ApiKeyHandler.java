package net.kevyporter.chromapixel.api.interaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import net.kevyporter.chromapixel.ChromaPixelMod;
import net.kevyporter.chromapixel.api.interaction.callbacks.ApiKeyLoadedCallback;
import net.kevyporter.chromapixel.util.ChatMessageComposer;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.client.FMLClientHandler;

public class ApiKeyHandler {
    private static ApiKeyHandler instance;

    public boolean loadingFailed = false;

    private String apiKey;
    private ApiKeyLoadedCallback callback;
    private static String API_KEY_STORAGE_PATH;
    private static String API_KEY_STORAGE_FILE;
    private static String API_KEY_REQUEST_MESSAGE_1 = "No API key found. This key is necessary for some cool features.";
    private static String API_KEY_REQUEST_MESSAGE_2_PART1 = "Simply do ";
    private static String API_KEY_REQUEST_MESSAGE_2_PART2 = " for creating a new one.";
    private static String API_KEY_REQUEST_MESSAGE_3 = "You can also add your key manually to config\\hypixel_api_key.txt.";
    private static String EMPTY_FILE_CONTENT = "Replace this with the api key or do /api on Hypixel Network. This File gets reset when a key doesn't work.";
    private static String API_KEY_PATTERN = "[a-f0-9]{8}[-]([a-f0-9]{4}[-]){3}[a-f0-9]{12}";

    static {
        try {
            API_KEY_STORAGE_PATH = FMLClientHandler.instance().getClient().mcDataDir.getCanonicalPath() + File.separatorChar + "config" +File.separatorChar;
            API_KEY_STORAGE_FILE = API_KEY_STORAGE_PATH + "hypixel_api_key.txt";
        } catch (IOException e) {
            ChromaPixelMod.instance().logError("Critical error when finding the api key file: ");
            e.printStackTrace();
        }
    }

    public ApiKeyHandler(ApiKeyLoadedCallback callback) {
        instance = this;
        this.callback = callback;
        new Thread() {
            @Override
            public void run() {
                ApiKeyHandler.instance.loadAPIKey();
            }
        }.start();
    }

    public void onChatMessage(String textMessage) {
        if(textMessage.startsWith("Your new API key is ")) {
            this.apiKey = textMessage.substring(textMessage.indexOf("is ") + 3);
            this.callback.ApiKeyLoaded(false, this.apiKey);
            new Thread() {
                @Override
                public void run() {
                    ApiKeyHandler.getInstance().saveAPIKey();
                };
            }.start();
            new ChatMessageComposer("API key successfully detected and saved. The API is ready for usage.", EnumChatFormatting.GREEN).send();
        }
    }

    public void requestApiKey() {
        new ChatMessageComposer(API_KEY_REQUEST_MESSAGE_1).send();
        new ChatMessageComposer(API_KEY_REQUEST_MESSAGE_2_PART1).appendMessage(new ChatMessageComposer("/api", EnumChatFormatting.RED).makeClickable(Action.RUN_COMMAND, "/api", new ChatMessageComposer("Runs ", EnumChatFormatting.GRAY).appendMessage(new ChatMessageComposer("/api", EnumChatFormatting.RED)))).appendMessage(new ChatMessageComposer(API_KEY_REQUEST_MESSAGE_2_PART2)).send();
        new ChatMessageComposer(API_KEY_REQUEST_MESSAGE_3).send();
    }

    private void loadAPIKey() {
        try {
            File path = new File(API_KEY_STORAGE_PATH);
            File file = new File(API_KEY_STORAGE_FILE);
            if(!path.exists()) {
                path.mkdirs();
            }

            if(!file.exists()) {
                file.createNewFile();
                this.resetApiFile(file);
                this.loadingFailed = true;
                this.callback.ApiKeyLoaded(true, null);
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String key = reader.readLine();
            reader.close();
            if(key == null || key.equals(EMPTY_FILE_CONTENT) || !this.isCorrectKeyFormat(key.replace(" ", ""))) {
                this.resetApiFile(file);
                this.loadingFailed = true;
                this.callback.ApiKeyLoaded(true, null);
                return;
            }
            this.apiKey = key.replace(" ", "");
            this.loadingFailed = false;
            this.callback.ApiKeyLoaded(false, this.apiKey);
        } catch (Exception e) {
            ChromaPixelMod.instance().logError("Critical error when reading the api key file: ");
            e.printStackTrace();
        }
    }

    private void saveAPIKey() {
        try {
            PrintWriter writer = new PrintWriter(new File(API_KEY_STORAGE_FILE));
            writer.write(this.apiKey);
            writer.close();
        } catch (Exception e) {
            ChromaPixelMod.instance().logError("Critical error when storing the api key file: ");
            e.printStackTrace();
        }
    }

    private void resetApiFile(File file) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(file);
        writer.write(EMPTY_FILE_CONTENT);
        writer.flush();
        writer.close();
    }

    private boolean isCorrectKeyFormat(String key) {
        return key.matches(API_KEY_PATTERN);
    }


    public static ApiKeyHandler getInstance() {
        return instance;
    }
}
