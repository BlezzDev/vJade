package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.texture.Texture;

import static org.lwjgl.opengl.GL11C.GL_LINEAR;
import static org.lwjgl.opengl.GL11C.GL_NEAREST;

public class Texture2D extends CanvasItem<Texture2D> implements SpriteProperties<Texture2D> {
    private Texture texture;
    private Filter filter = Filter.LINEAR;

    public enum Filter {
        LINEAR(GL_LINEAR), NEAREST(GL_NEAREST);

        final int gl;

        Filter(int gl) {
            this.gl = gl;
        }

        public int getGl() {
            return gl;
        }
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (VJade.existContext()) {
            texture.getRenderer().draw(this);
        }
    }

    @Override
    public void finish() {
        super.finish();

        texture.getRenderer().cleanup();
    }

    public Texture2D setTexture(String path) { setTexture(new Texture(path)); return this; }
    public Texture2D setTexture(Texture texture) {
        texture.getRenderer().cleanup();

        this.texture = texture;

        texture.getRenderer().loadTexGeometry(getPivot(), isHorizontalFlip());
        texture.getRenderer().loadTexture(filter, isVerticalFlip());
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
