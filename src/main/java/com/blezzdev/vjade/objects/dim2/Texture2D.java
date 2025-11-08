package com.blezzdev.vjade.objects.dim2;

import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.core.manager.canvas.batch.Batch;
import com.blezzdev.vjade.objects.canvas.CanvasEntity;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.geometry.Vec3;

import java.util.Objects;

public class Texture2D extends CanvasEntity<Texture2D> {
    private String lastTexture;

    @Override
    public void render(CanvasManager canvas) {
        super.render(canvas);

        draw(canvas);

        if (!Objects.equals(getTexture().getResourcePath(), lastTexture)) {
            lastTexture = getTexture().getResourcePath();

            updateTexture();
        }
    }

    private void updateTexture() {
        getTexture().load();
    }

    private void draw(CanvasManager canvas) {
        canvas.getBatch().draw(getShader(), getTexture(),

                new Vec3(getPosition().x, getPosition().y, getzIndex()),
                getSize(), getPivot(),

                getModulate(), getSizeBehavior(),
                getRotation(), canvas.getView());
    }
}
