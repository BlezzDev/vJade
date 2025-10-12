package com.blezzdev.vjade.tools.sound;

import org.lwjgl.openal.AL10;

import static org.lwjgl.openal.AL10.*;

class RawSoundBuilder {
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

    public float getDuration() {
        int size = alGetBufferi(buffer, AL_SIZE);
        int channels = alGetBufferi(buffer, AL_CHANNELS);
        int bits = alGetBufferi(buffer, AL_BITS);
        int freq = alGetBufferi(buffer, AL_FREQUENCY);

        int bytesPerSample = (bits / 8) * channels;
        return (float) size / (freq * bytesPerSample);
    }

    protected void changeVolume(float volume) {
        alSourcef(source, AL_GAIN, volume);
    }

    protected void changePitch(float pitch) {
        alSourcef(source, AL_PITCH, pitch);
    }

    public void stop() {
        alSourceStop(source);
    }

    public void play() {
        alSourcePlay(source);
    }

    public void cleanup() {
        alDeleteSources(source);
        alDeleteBuffers(buffer);
    }
}
