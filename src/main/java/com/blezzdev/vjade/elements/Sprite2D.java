package com.blezzdev.vjade.elements;

import com.blezzdev.vjade.core.engine.Engine;
import com.blezzdev.vjade.tools.Vector2;

public class Sprite2D extends Node2D {
    private String texturePath;
    private int textureID;

    private boolean visible = true;
    private Vector2 size;
    private Engine engine;

    public Sprite2D(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void render() {
        if (visible && engine != null) {
            this.textureID = engine.getRenderer().getTextureRenderer().loadTexture(texturePath);
            engine.getRenderer().getTextureRenderer().draw(this.textureID, getPosition(), getSize());
        }
    }

    public Vector2 getSize() {
        return size;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setTexture(String texturePath) {
        this.texturePath = texturePath;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
