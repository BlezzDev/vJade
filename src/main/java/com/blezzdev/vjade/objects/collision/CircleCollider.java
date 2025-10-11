package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.tools.data.geometry.Vector2;
import com.blezzdev.vjade.util.types.Shapes;

public class CircleCollider extends Collider {
    public Vector2 position;
    public float radius;

    public CircleCollider() { this(0, 0, 0); }
    public CircleCollider(float x, float y, float radius) { this(new Vector2(x, y), radius); }
    public CircleCollider(Vector2 position, float radius) {
        super(Shapes.CIRCLE);

        this.position = position;
        this.radius = radius;
    }

    public CircleCollider setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public CircleCollider setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }
}
