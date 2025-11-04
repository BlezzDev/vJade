package com.blezzdev.vjade.core.manager.canvas;

import com.blezzdev.vjade.core.manager.canvas.batch.Batch;
import com.blezzdev.vjade.objects.canvas.CanvasEntity;
import com.blezzdev.vjade.tools.data.geometry.Vec3;

import java.util.ArrayList;
import java.util.List;

public class CanvasManager {
    private final List<CanvasEntity<?>> entities = new ArrayList<>();
    private Batch batch = new Batch();

    public void addEntity(CanvasEntity<?> entity) {
        entities.add(entity);
    }

    public void removeEntity(CanvasEntity<?> entity) {
        entities.remove(entity);
    }

    public void init() {}

    public void update() {

        // Draw on batch.

        batch.begin(null); // Adds a default shader.
        entities.forEach(entity ->
            batch.draw(entity.getTexture(),
                    new Vec3(entity.getPosition().x, entity.getPosition().y, 0),
                    entity.getSize(), entity.getPivot(), entity.getModulate(), entity.getSizeBehavior(),
                    entity.getRotation(), entity.getzIndex())
        );
        batch.end();
    }
}