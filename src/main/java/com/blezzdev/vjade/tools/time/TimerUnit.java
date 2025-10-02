package com.blezzdev.vjade.tools.time;

public class TimerUnit {
    private final Runnable function;
    private final float timeWait;
    private float currentTime;

    public TimerUnit(float timeWait, Runnable function) {
        this.timeWait = timeWait;
        this.function = function;
    }

    public void exec() { function.run(); }

    public void step(float deltaTime) { currentTime += deltaTime; }

    public float getTimeWait() {
        return timeWait;
    }

    public float getCurrentTime() {
        return currentTime;
    }
}
