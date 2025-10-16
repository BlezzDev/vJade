package com.blezzdev.vjade.tools.data.render;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public class TextureRegion extends Texture {
    protected Vec2 position;
    protected String uuid;

    public TextureRegion(String resourcePath) {
        super(resourcePath);
    }

    public String getUUID() {
        return uuid;
    }

    public Vec2 getPosition() {
        return position;
    }
}
