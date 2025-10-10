package com.blezzdev.vjade.core.manager;

class Timer {
    private final Runnable function;
    private final float timeWait;
    private float currentTime;

    public Timer(float timeWait, Runnable function) {
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
