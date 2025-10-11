package com.blezzdev.vjade.tools.sound;

import org.lwjgl.openal.AL10;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.system.MemoryStack.stackPush;

class SoundBuilder {
    private int buffer, source;

    protected void genSoundData() {
        buffer = AL10.alGenBuffers();
        source = AL10.alGenSources();
    }

    protected void loadSound(String path) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer channelsBuffer = stack.mallocInt(1);
            IntBuffer sampleRateBuffer = stack.mallocInt(1);

            ShortBuffer rawAudioBuffer = STBVorbis.stb_vorbis_decode_filename(path, channelsBuffer, sampleRateBuffer);

            if (rawAudioBuffer == null) {
                throw new RuntimeException("Failed to load OGG file: " + path);
            }

            int channels = channelsBuffer.get(0);
            int sampleRate = sampleRateBuffer.get(0);

            int format;
            if (channels == 1) format = AL_FORMAT_MONO16;
            else if (channels == 2) format = AL_FORMAT_STEREO16;
            else throw new IllegalStateException("Unsupported channel count: " + channels);

            alBufferData(buffer, format, rawAudioBuffer, sampleRate);
            alSourcei(source, AL_BUFFER, buffer);

            MemoryUtil.memFree(rawAudioBuffer);
        }
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
