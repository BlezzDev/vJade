package com.blezzdev.vjade.tools.canvas;

import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.util.types.Filter;

public class Image {
    final ImageLoader loader = new ImageLoader();

    private Vec2 size = new Vec2();
    private String resourcePath;

    public Image(String resourcePath) {
        setResourcePath(resourcePath);
    }

    public void load() {
        loader.load(resourcePath, size, Filter.LINEAR);
    }
    public void destroy() {
        loader.destroy();
    }

    public Image setResourcePath(String path) {
        this.resourcePath = path;
        return this;
    }

    public int getId() {
        return loader.id;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public Vec2 getSize() {
        return size;
    }
}
