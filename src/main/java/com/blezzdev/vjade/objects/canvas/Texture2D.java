package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.render.Texture;
import com.blezzdev.vjade.tools.render.TextureDraw;
import com.blezzdev.vjade.util.types.Filter;

import java.util.Objects;

public class Texture2D extends CanvasItem<Texture2D> {
    private TextureDraw texPainter;

    boolean horizontalFlip = false, verticalFlip = false;
    int frame = 0, horizontalDivisions = 0, verticalDivisions = 0;

    private String lastPath;
    private Texture texture;
    private Filter filter = Filter.LINEAR;

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (texPainter == null) {
            texPainter = new TextureDraw(texture);
        } else {
            if (!Objects.equals(lastPath, texture.getResourcePath())) {
                texPainter.cleanup();

                texPainter.loadTexGeometry(isHorizontalFlip(), getFrame(), getVerticalDivisions(), getHorizontalDivisions());
                texPainter.loadTexture(filter, isVerticalFlip());

                lastPath = texture.getResourcePath();
            }

            if (VJade.existContext()) {
                texPainter.draw(this);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();

        texPainter.cleanup();
    }

    public Texture2D setTexture(String path) { setTexture(new Texture(path)); return this; }
    public Texture2D setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public Texture2D setFrame(int frame) {
        this.frame = frame; return this;
    }

    public Texture2D setHorizontalDivisions(int horizontalDivisions) {
        this.horizontalDivisions = horizontalDivisions; return this;
    }

    public Texture2D setHorizontalFlip(boolean horizontalFlip) {
        this.horizontalFlip = horizontalFlip; return this;
    }

    public Texture2D setVerticalDivisions(int verticalDivisions) {
        this.verticalDivisions = verticalDivisions; return this;
    }

    public Texture2D setVerticalFlip(boolean verticalFlip) {
        this.verticalFlip = verticalFlip; return this;
    }

    public Texture2D setFlips(boolean vertical, boolean horizontal) {
        setVerticalFlip(vertical);
        setHorizontalFlip(horizontal); return this;
    }

    public Texture2D setDivisions(int vertical, int horizontal) {
        setVerticalDivisions(vertical);
        setHorizontalDivisions(horizontal); return this;
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

    public int getFrame() {
        return frame;
    }

    public int getHorizontalDivisions() {
        return horizontalDivisions;
    }

    public int getVerticalDivisions() {
        return verticalDivisions;
    }

    public boolean isHorizontalFlip() {
        return horizontalFlip;
    }

    public boolean isVerticalFlip() { return verticalFlip; }
}
