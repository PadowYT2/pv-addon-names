package ru.padow.names;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;

import lombok.Getter;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import su.plo.voice.api.server.PlasmoVoiceServer;

public class Names implements DedicatedServerModInitializer {
    public static final String MOD_ID = "pv-addon-names";

    @Getter private static Config config = Config.get();
    private final PlasmoVoice addon = new PlasmoVoice();

    @Override
    public void onInitializeServer() {
        PlasmoVoiceServer.getAddonsLoader().load(addon);

        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> {
                    dispatcher.register(
                            CommandManager.literal("name")
                                    .requires(source -> source.hasPermissionLevel(4))
                                    .then(
                                            CommandManager.literal("set")
                                                    .then(
                                                            CommandManager.argument(
                                                                            "target",
                                                                            EntityArgumentType
                                                                                    .player())
                                                                    .then(
                                                                            CommandManager.argument(
                                                                                            "name",
                                                                                            StringArgumentType
                                                                                                    .greedyString())
                                                                                    .executes(
                                                                                            context -> {
                                                                                                ServerPlayerEntity
                                                                                                        target =
                                                                                                                EntityArgumentType
                                                                                                                        .getPlayer(
                                                                                                                                context,
                                                                                                                                "target");
                                                                                                String
                                                                                                        name =
                                                                                                                StringArgumentType
                                                                                                                        .getString(
                                                                                                                                context,
                                                                                                                                "name");
                                                                                                config
                                                                                                        .players
                                                                                                        .put(
                                                                                                                target
                                                                                                                        .getUuid(),
                                                                                                                name);
                                                                                                config
                                                                                                        .save();
                                                                                                context.getSource()
                                                                                                        .sendFeedback(
                                                                                                                () ->
                                                                                                                        Text
                                                                                                                                .literal(
                                                                                                                                        "Done,"
                                                                                                                                            + " the target"
                                                                                                                                            + " now has"
                                                                                                                                            + " to rejoin"
                                                                                                                                            + " the server"),
                                                                                                                false);
                                                                                                return Command
                                                                                                        .SINGLE_SUCCESS;
                                                                                            }))))
                                    .then(
                                            CommandManager.literal("reset")
                                                    .then(
                                                            CommandManager.argument(
                                                                            "target",
                                                                            EntityArgumentType
                                                                                    .player())
                                                                    .executes(
                                                                            context -> {
                                                                                ServerPlayerEntity
                                                                                        target =
                                                                                                EntityArgumentType
                                                                                                        .getPlayer(
                                                                                                                context,
                                                                                                                "target");
                                                                                config.players
                                                                                        .remove(
                                                                                                target
                                                                                                        .getUuid());
                                                                                config.save();
                                                                                context.getSource()
                                                                                        .sendFeedback(
                                                                                                () ->
                                                                                                        Text
                                                                                                                .literal(
                                                                                                                        "Done,"
                                                                                                                            + " the target"
                                                                                                                            + " now has"
                                                                                                                            + " to rejoin"
                                                                                                                            + " the server"),
                                                                                                false);
                                                                                return Command
                                                                                        .SINGLE_SUCCESS;
                                                                            }))));
                });
    }
}
