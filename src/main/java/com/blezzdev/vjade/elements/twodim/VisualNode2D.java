package com.blezzdev.vjade.elements.twodim;

import com.blezzdev.vjade.tools.Vector2;
import com.blezzdev.vjade.tools.color.Color;

public class VisualNode2D extends Node2D {
    private boolean visible = true;
    private Color module = Color.WHITE;
    private Vector2 size = Vector2.ZERO;

    public Vector2 getSize() {
        return size;
    }
    public Color getModule() { return module; }
    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setModule(Color module) { this.module = module; }
    public void setSize(Vector2 size) {
        this.size = size;
    }
}
