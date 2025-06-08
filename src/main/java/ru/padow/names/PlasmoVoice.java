package ru.padow.names;

import org.jetbrains.annotations.NotNull;

import su.plo.voice.api.addon.AddonInitializer;
import su.plo.voice.api.addon.InjectPlasmoVoice;
import su.plo.voice.api.addon.annotation.Addon;
import su.plo.voice.api.event.EventSubscribe;
import su.plo.voice.api.server.PlasmoVoiceServer;
import su.plo.voice.api.server.event.player.PlayerInfoCreateEvent;
import su.plo.voice.proto.data.player.VoicePlayerInfo;

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
    public void onPlayerInfoCreate(@NotNull PlayerInfoCreateEvent event) {
        VoicePlayerInfo playerInfo = event.getVoicePlayerInfo();

        event.setVoicePlayerInfo(
                new VoicePlayerInfo(
                        playerInfo.getPlayerId(),
                        Names.getConfig()
                                .players
                                .getOrDefault(playerInfo.getPlayerId(), playerInfo.getPlayerNick()),
                        playerInfo.isMuted(),
                        playerInfo.isVoiceDisabled(),
                        playerInfo.isMicrophoneMuted()));
    }
}
