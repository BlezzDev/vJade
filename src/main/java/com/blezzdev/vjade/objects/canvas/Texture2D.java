package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.texture.Texture;
import com.blezzdev.vjade.tools.texture.TextureRenderer;
import com.blezzdev.vjade.util.types.Filter;

import java.util.Objects;

public class Texture2D extends CanvasItem<Texture2D> implements SpriteProperties<Texture2D> {
    private TextureRenderer textureRenderer;

    private String lastPath;
    private Texture texture;
    private Filter filter = Filter.LINEAR;

    @Override
    public void start() {
        super.start();

        if (textureRenderer == null) {
            textureRenderer = new TextureRenderer(texture);
        }
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (!Objects.equals(lastPath, texture.getResourcePath())) {
            textureRenderer.cleanup();

            textureRenderer.loadTexGeometry(isHorizontalFlip(), getFrame(), getVerticalDivisions(), getHorizontalDivisions());
            textureRenderer.loadTexture(filter, isVerticalFlip());

            lastPath = texture.getResourcePath();
        }

        if (VJade.existContext()) {
            textureRenderer.draw(this);
        }
    }

    @Override
    public void finish() {
        super.finish();

        textureRenderer.cleanup();
    }

    public Texture2D setTexture(String path) { setTexture(new Texture(path)); return this; }
    public Texture2D setTexture(Texture texture) {
        this.texture = texture;
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
