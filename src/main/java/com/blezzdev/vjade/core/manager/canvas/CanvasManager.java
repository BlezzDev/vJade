package com.blezzdev.vjade.core.manager.canvas;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.canvas.batch.Batch;
import com.blezzdev.vjade.tools.data.geometry.Vec2;

public class CanvasManager {
    private final Batch batch = new Batch();

    private Vec2 view = new Vec2();
    private float zoom;

    public void init() {
        zoom = 1;
    }

    public void update(float deltaTime, ScreenManager screenManager) {

        // Draw on batch.

        batch.begin(); // Adds a default shader.

        screenManager.render(deltaTime, this);

        batch.end();
    }

    public void clear() { batch.cleanup(); }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    public void setView(Vec2 offset) {
        this.view = offset;
    }

    public Batch getBatch() {
        return batch;
    }
    public Vec2 getView() {
        return view;
    }
    public float getZoom() {
        return zoom;
    }
}