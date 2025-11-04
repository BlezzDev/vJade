package com.blezzdev.vjade.tools.data.geometry;

public class Rect2 {
    Vec2 position = new Vec2();
    Vec2 size = new Vec2();

    public Rect2() { this(0, 0); }
    public Rect2(float width, float height) { this(0, 0, width, height); }
    public Rect2(float x, float y, float width, float height) {
        set(x, y, width, height);
    }

    public Vec2 getPosition() {
        return position;
    }

    public Vec2 getSize() {
        return size;
    }

    public Rect2 set(float x, float y, float width, float height) {
        position.set(x, y);
        size.set(width, height);
        return this;
    }

    public Rect2 setSize(Vec2 size) {
        this.size = size;
        return this;
    }

    public Rect2 setPosition(Vec2 position) {
        this.position = position;
        return this;
    }
}
