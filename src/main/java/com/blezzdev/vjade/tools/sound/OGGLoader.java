package com.blezzdev.vjade.tools.sound;

import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.alSourcei;
import static org.lwjgl.system.MemoryStack.stackPush;

class OGGLoader {
    public OGGLoader(String path, int buffer, int source) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer channelsBuffer = stack.mallocInt(1);
            IntBuffer sampleRateBuffer = stack.mallocInt(1);

            ShortBuffer rawAudioBuffer = STBVorbis.stb_vorbis_decode_filename(path, channelsBuffer, sampleRateBuffer);

            if (rawAudioBuffer == null) {
                throw new RuntimeException("Failed to load OGG file: " + path);
            }

            build(rawAudioBuffer, channelsBuffer, sampleRateBuffer, buffer);

            alSourcei(source, AL_BUFFER, buffer);
            MemoryUtil.memFree(rawAudioBuffer);
        }
    }

    private void build(ShortBuffer rawAudioBuffer, IntBuffer channelsBuffer, IntBuffer sampleRateBuffer, int buffer) {
        int channels = channelsBuffer.get(0);
        int sampleRate = sampleRateBuffer.get(0);

        int format;
        if (channels == 1) format = AL_FORMAT_MONO16;
        else if (channels == 2) format = AL_FORMAT_STEREO16;
        else throw new IllegalStateException("Unsupported channel count: " + channels);

        alBufferData(buffer, format, rawAudioBuffer, sampleRate);
    }
}
