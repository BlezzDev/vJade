package com.blezzdev.vjade.objects.audio;

import com.blezzdev.vjade.objects.build.item.VJObject;
import com.blezzdev.vjade.tools.sound.Sound;

public class AudioPlayer extends VJObject {
    private final Sound sound = new Sound();
    private String lastPath = "";

    private float volume = 1;
    private float pitch = 1;

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (!lastPath.equals(sound.getResourcePath())) {
            sound.load();
            sound.setVolume(volume);
            sound.setPitch(pitch);

            lastPath = sound.getResourcePath();
        }
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
        return this;
    }

    public AudioPlayer setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public AudioPlayer setAudio(String path) {
        sound.setResourcePath(path);
        return this;
    }

    public String getAudio() {
        return sound.getResourcePath();
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
