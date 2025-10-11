package com.blezzdev.vjade.tools.sound;

import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.AL10.*;

class SoundBuilder {
    private int buffer, source;

    protected void genSoundData() {
        buffer = AL10.alGenBuffers();
        source = AL10.alGenSources();
    }

    protected void loadSound(String path) {
        if (path.endsWith(".ogg")) {
            new OGGLoader(path, buffer, source);
        }
    }

    protected void changeVolume(float volume) {
        alSourcef(source, AL_GAIN, volume);
    }

    public void stop() {
        alSourceStop(source);
    }

    public void play() {
        alSourcePlay(source);

        while (alGetSourcei(source, AL_SOURCE_STATE) == AL_PLAYING) {
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        }
    }

    public void cleanup() {
        alDeleteSources(source);
        alDeleteBuffers(buffer);
    }
}
