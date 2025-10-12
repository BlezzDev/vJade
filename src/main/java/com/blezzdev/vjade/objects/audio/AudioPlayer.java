package com.blezzdev.vjade.objects.audio;

import com.blezzdev.vjade.objects.build.item.VJObject;
import com.blezzdev.vjade.tools.sound.Sound;

public class AudioPlayer extends VJObject {
    private final Sound sound = new Sound();

    private String resourcePath;
    private float volume = 1;
    private float pitch = 1;

    private void updateProperties() {
        sound.load(resourcePath);

        sound.setVolume(volume);
        sound.setPitch(pitch);
    }

    @Override
    public void finish() {
        super.finish();

        sound.cleanup();
    }

    public void play() {
        sound.play();
    }

    public void stop() {
        sound.stop();
    }

    public AudioPlayer setVolume(int volume) {
        this.volume = volume;
        updateProperties();
        return this;
    }

    public AudioPlayer setPitch(float pitch) {
        this.pitch = pitch;
        updateProperties();
        return this;
    }

    public AudioPlayer setAudio(String path) {
        this.resourcePath = path;
        updateProperties();
        return this;
    }

    public String getAudio() {
        return resourcePath;
    }

    public float getAudioDuration() {
        return sound.getDuration();
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }
}
