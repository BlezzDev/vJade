package com.blezzdev.vjade.objects.dim2;

import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.objects.canvas.CanvasEntity;

import java.util.Objects;

public class Texture2D extends CanvasEntity<Texture2D> {
    private String lastTexture;

    @Override
    public void render(float deltaTime, CanvasManager canvas) {
        super.render(deltaTime, canvas);

        if (getTexture() != null) {
            draw(canvas);

            if (!Objects.equals(getTexture().getResourcePath(), lastTexture)) {
                lastTexture = getTexture().getResourcePath();

                updateTexture();
            }
        }
    }

    private void updateTexture() {
        getTexture().load();
    }

    private void draw(CanvasManager canvas) {
        canvas.getBatch().draw(getShader(), getTexture(),

                getPosition(),
                getSize(), getPivot(),

                getModulate(), getSizeBehavior(),
                getRotation(), getzIndex(),

                canvas.getZoom(),
                canvas.getView());
    }
}
