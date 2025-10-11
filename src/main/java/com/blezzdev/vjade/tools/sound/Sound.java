package com.blezzdev.vjade.tools.sound;

public class Sound extends SoundBuilder {
    private String resourcePath;
    private float volume;

    public Sound(String resourcePath) {
        genSoundData();
        setResourcePath(resourcePath);
    }

    public Sound setVolume(float volume) {
        this.volume = volume;
        changeVolume(volume);
        return this;
    }

    public Sound setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        loadSound(resourcePath);
        return this;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public float getVolume() {
        return volume;
    }
}
