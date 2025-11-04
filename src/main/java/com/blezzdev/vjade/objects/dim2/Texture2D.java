package com.blezzdev.vjade.objects.dim2;

import com.blezzdev.vjade.objects.canvas.CanvasEntity;

import java.util.Objects;

public class Texture2D extends CanvasEntity<Texture2D> {
    private String lastTexture;

    @Override
    public void start() {
        super.start();
        addToCanvas();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (!Objects.equals(getTexture().getResourcePath(), lastTexture)) {
            lastTexture = getTexture().getResourcePath();

            addToCanvas();
        }
    }

    private void addToCanvas() {
        getTexture().load();
        getCanvas().addEntity(this);
    }
}
