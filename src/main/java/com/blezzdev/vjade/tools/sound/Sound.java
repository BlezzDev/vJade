package com.blezzdev.vjade.tools.sound;

public class Sound extends SoundBuilder {
    private String resourcePath;

    public void load() {
        genSoundData();
        setResourcePath(resourcePath);
    }

    public void setVolume(float volume) {
        changeVolume(volume);
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        loadSound(resourcePath);
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
