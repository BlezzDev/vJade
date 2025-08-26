package com.blezzdev.vjade.elements.twodim;

import com.blezzdev.vjade.core.engine.Engine;

public class Sprite2D extends VisualNode2D {
    private String texturePath;
    private int textureID;

    private Engine engine;

    public Sprite2D(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void render() {
        if (isVisible() && engine != null) {
            this.textureID = engine.getResources().getTextureRenderer().loadTexture(texturePath);
            engine.getResources().getTextureRenderer().draw(this.textureID, getPosition(), getSize(), getRotation(), getModule());
        }
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexture(String texturePath) {
        this.texturePath = texturePath;
    }
}
