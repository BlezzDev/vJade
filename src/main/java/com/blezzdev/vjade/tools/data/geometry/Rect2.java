package com.blezzdev.vjade.tools.data.geometry;

public class Rect2 extends Vec2 {
    public float width, height;

    public Rect2() { this(0); }
    public Rect2(float i) { this(i, i, i, i); }
    public Rect2(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + width + ", " + height + ")";
    }
}
