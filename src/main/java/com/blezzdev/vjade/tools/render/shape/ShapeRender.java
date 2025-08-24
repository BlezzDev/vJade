package com.blezzdev.vjade.tools.render.shape;

import com.blezzdev.vjade.tools.color.Color;

public class ShapeRender {
    private Color color;

    public ShapeRender(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void render() {}
}
