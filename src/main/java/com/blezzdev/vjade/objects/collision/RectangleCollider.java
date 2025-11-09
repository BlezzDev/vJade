package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.util.types.Shapes;

public class RectangleCollider extends Collider {
    private Vec2 position;
    private Vec2 size;

    public RectangleCollider() { this(0, 0, 0, 0); }
    public RectangleCollider(float x, float y, float w, float h) {
        this(new Vec2(x, y), new Vec2(w, h));
    }
    public RectangleCollider(Vec2 position, Vec2 size) {
        super(Shapes.RECTANGLE);
    }

    public RectangleCollider setPosition(float x, float y) { setPosition(new Vec2(x, y)); return this; }
    public RectangleCollider setPosition(Vec2 position) {
        this.position = position;
        return this;
    }

    public RectangleCollider setSize(float width, float height) { setSize(new Vec2(width, height)); return this; }
    public RectangleCollider setSize(Vec2 size) {
        this.size = size;
        return this;
    }

    public Vec2 getPosition() {
        return position;
    }

    public Vec2 getSize() {
        return size;
    }
}