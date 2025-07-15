package ru.padow.names;

import org.jetbrains.annotations.NotNull;

import su.plo.slib.api.server.entity.player.McServerPlayer;
import su.plo.voice.api.addon.AddonInitializer;
import su.plo.voice.api.addon.InjectPlasmoVoice;
import su.plo.voice.api.addon.annotation.Addon;
import su.plo.voice.api.event.EventSubscribe;
import su.plo.voice.api.server.PlasmoVoiceServer;
import su.plo.voice.api.server.audio.source.ServerAudioSource;
import su.plo.voice.api.server.audio.source.ServerPlayerSource;
import su.plo.voice.api.server.event.audio.source.ServerSourceCreatedEvent;

@Addon(
        id = "pv-addon-names",
        name = "pv-addon-names",
        version = "1.0.0",
        authors = {"PadowYT2"})
public class PlasmoVoice implements AddonInitializer {
    @InjectPlasmoVoice private PlasmoVoiceServer voiceServer;

    @Override
    public void onAddonInitialize() {}

    @Override
    public void onAddonShutdown() {}

    @EventSubscribe
    public void onServerSourceCreated(@NotNull ServerSourceCreatedEvent event) {
        ServerAudioSource<?> source = event.getSource();
        if (!(source instanceof ServerPlayerSource)) return;

        McServerPlayer player = ((ServerPlayerSource) source).getPlayer().getInstance();
        source.setName(Names.getConfig().players.getOrDefault(player.getUuid(), player.getName()));
    }
}
