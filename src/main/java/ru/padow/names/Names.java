package ru.padow.names;

import lombok.Getter;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.server.network.ServerPlayerEntity;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.fabric.FabricLamp;
import revxrsal.commands.fabric.actor.FabricCommandActor;

import su.plo.voice.api.server.PlasmoVoiceServer;

public class Names implements DedicatedServerModInitializer {
    public static final String MOD_ID = "pv-addon-names";

    @Getter private static Config config = Config.get();
    private final PlasmoVoice addon = new PlasmoVoice();

    @Override
    public void onInitializeServer() {
        FabricLamp.builder().build().register(new Commands());
        PlasmoVoiceServer.getAddonsLoader().load(addon);
    }

    @Command("name")
    public class Commands {
        @Subcommand("set")
        public void set(FabricCommandActor actor, ServerPlayerEntity target, String name) {
            config.players.put(target.getUuid(), name);
            config.save();
            actor.reply("Done, the target now has to rejoin the server");
        }

        @Subcommand("reset")
        public void reset(FabricCommandActor actor, ServerPlayerEntity target) {
            config.players.remove(target.getUuid());
            config.save();
            actor.reply("Done, the target now has to rejoin the server");
        }
    }
}
