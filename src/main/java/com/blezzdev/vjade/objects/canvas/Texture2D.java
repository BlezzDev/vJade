package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.texture.Texture;

public class Texture2D extends CanvasItem<Texture2D> {
    private Texture texture;
    private Filter filter = Filter.LINEAR;

    public enum Filter {
        LINEAR, NEAREST
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (VJade.existContext()) {
            if (texture == null) {
                getLogger().warn("No hay textura asignada.");
                return;
            }

            getLogger().info("Dibujando textura...");
            texture.getRenderer().draw(this);
        } else {
            getLogger().warn("No existe contexto de VJade.");
        }
    }

    @Override
    public void finish() {
        super.finish();

        texture.getRenderer().cleanup();
    }

    public Texture2D setTexture(Texture texture) {
        texture.getRenderer().cleanup();

        this.texture = texture;

        texture.getRenderer().loadTexGeometry(getPivot());
        texture.getRenderer().loadTexture();
        texture.getRenderer().loadUniforms();

        return this;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Texture getTexture() {
        return texture;
    }

    public Filter getFilter() {
        return filter;
    }
}
