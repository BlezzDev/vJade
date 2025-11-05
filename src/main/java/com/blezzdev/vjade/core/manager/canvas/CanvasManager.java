package com.blezzdev.vjade.core.manager.canvas;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.canvas.batch.Batch;
import com.blezzdev.vjade.objects.canvas.CanvasEntity;
import com.blezzdev.vjade.tools.data.geometry.Vec3;

import java.util.ArrayList;
import java.util.List;

public class CanvasManager {
    private Batch batch = new Batch();

    public void init() {}

    public void update(ScreenManager screenManager) {

        // Draw on batch.

        batch.begin(null); // Adds a default shader.

        screenManager.render(this);

        batch.end();
    }

    public Batch getBatch() {
        return batch;
    }
}