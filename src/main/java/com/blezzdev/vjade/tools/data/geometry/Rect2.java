package com.blezzdev.vjade.tools.data.geometry;

public class Rect2 extends Vector2 {
    public Float width, height;

    public Rect2() { this(0, 0, 0, 0); }
    public Rect2(float x, float y, float w, float h) {
        super(x, y);
        this.width = w;
        this.height = h;
    }
}
