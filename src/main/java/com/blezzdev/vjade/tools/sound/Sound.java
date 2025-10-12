package com.blezzdev.vjade.tools.sound;

public class Sound extends RawSoundBuilder {
    public void load(String resourcePath) {
        genSoundData();
        loadSound(resourcePath);
    }

    public void setVolume(float volume) {
        changeVolume(volume);
    }

    public void setPitch(float pitch) {
        changePitch(pitch);
    }
}
