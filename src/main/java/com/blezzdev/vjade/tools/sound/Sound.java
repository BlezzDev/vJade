package com.blezzdev.vjade.tools.sound;

public class Sound extends SoundBuilder {
    private String resourcePath;

    public Sound(String resourcePath) {
        genSoundData();
        setResourcePath(resourcePath);
    }

    public Sound setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;

        loadSound(resourcePath);

        return this;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
