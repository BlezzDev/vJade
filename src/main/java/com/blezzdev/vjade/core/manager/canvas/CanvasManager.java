package com.blezzdev.vjade.core.manager.canvas;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.canvas.batch.Batch;
import com.blezzdev.vjade.objects.canvas.CanvasEntity;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.geometry.Rect2;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.geometry.Vec3;

import java.util.ArrayList;
import java.util.List;

public class CanvasManager {
    private final Batch batch = new Batch();
    private final Rect2 border = new Rect2();

    public void init() {
        border.setPosition(new Vec2(0, 0)).setSize(VJade.getContext().getSize());
    }

    public void update(ScreenManager screenManager) {

        // Draw on batch.

        batch.begin(null); // Adds a default shader.

        screenManager.render(this);

        batch.end();
    }

    public void clear() { batch.cleanup(); }

    public Batch getBatch() {
        return batch;
    }
    public Rect2 getBorder() {
        return border;
    }
}