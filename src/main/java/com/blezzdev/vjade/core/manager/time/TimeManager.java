package com.blezzdev.vjade.core.manager.time;

import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.manager.Manager;

import java.util.ArrayList;
import java.util.List;

public class TimeManager extends Manager {
    private final List<Float> timers = new ArrayList<>();

    private int cycle;
    private int fps;

    private long lastTime;
    private long timer;

    private float deltaTime;

    /**
     * Creates a new manager associated with the given application.
     *
     * @param application the application instance controlled or used by this manager
     */
    public TimeManager(GameApplication application) {
        super(application);
    }

    @Override
    protected void init() {
        cycle = 0;
        lastTime = System.nanoTime();
        timer = System.currentTimeMillis();
    }

    @Override
    protected void update() {
        calculateDeltaTime();
    }

    @Override
    protected void postUpdate() {}

    @Override
    protected void end() {}

    private void calculateDeltaTime() {
        long now = System.nanoTime();
        deltaTime = (now - lastTime) / 1_000_000_000.0f;
        lastTime = now;

        if (System.currentTimeMillis() - timer >= 1000) {
            timer += 1000;
            fps = cycle;
            cycle = 0;
        }

        cycle++;
    }

    public float getDeltaTime() { return deltaTime; }
    public int getFps() { return fps; }
}