package com.blezzdev.vjade.tools.sound;

public class Sound extends RawSoundBuilder {
    private boolean loaded = false;

    public void load(String resourcePath) {
        if (loaded) return;
        loaded = true;

        genSoundData();
        loadSound(resourcePath);
    }

    public boolean isLoaded() { return loaded; }

    public void setVolume(float volume) {
        changeVolume(volume);
    }

    public void setPitch(float pitch) {
        changePitch(pitch);
    }
}
