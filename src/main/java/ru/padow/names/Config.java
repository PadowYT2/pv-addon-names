package ru.padow.names;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_FILE =
            FabricLoader.getInstance().getConfigDir().resolve("players.json");

    private static Config INSTANCE;
    public Map<UUID, String> players;

    private Config() {
        this.players = new HashMap<>();
    }

    public static Config get() {
        if (INSTANCE == null) INSTANCE = load();
        return INSTANCE;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
            GSON.toJson(this, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Config load() {
        Config config;

        if (Files.exists(CONFIG_FILE)) {
            try (FileReader reader = new FileReader(CONFIG_FILE.toFile())) {
                config = GSON.fromJson(reader, new TypeToken<Config>() {}.getType());
                if (config.players == null) config.players = new HashMap<>();
            } catch (IOException e) {
                config = new Config();
            }
        } else {
            config = new Config();
        }

        config.save();
        return config;
    }
}
