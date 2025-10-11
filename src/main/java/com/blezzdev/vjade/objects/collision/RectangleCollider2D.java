package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.tools.data.geometry.Vector2;
import com.blezzdev.vjade.util.types.Shapes;

public class RectangleCollider2D extends Collider {
    private Vector2 position;
    private Vector2 size;

    public RectangleCollider2D(float x, float y, float w, float h) {
        this(new Vector2(x, y), new Vector2(w, h));
    }
    public RectangleCollider2D(Vector2 position, Vector2 size) {
        super(Shapes.RECTANGLE);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }
}
