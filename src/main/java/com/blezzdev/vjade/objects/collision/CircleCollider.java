package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.util.types.Shapes;

public class CircleCollider extends Collider {
    private Vec2 position;
    private float radius;

    public CircleCollider() { this(0, 0, 0); }
    public CircleCollider(float x, float y, float radius) { this(new Vec2(x, y), radius); }
    public CircleCollider(Vec2 position, float radius) {
        super(Shapes.CIRCLE);

        setPosition(position);
        setRadius(radius);
    }

    public CircleCollider setPosition(float x, float y) { setPosition(new Vec2(x, y)); return this; }
    public CircleCollider setPosition(Vec2 position) {
        this.position = position;
        return this;
    }

    public CircleCollider setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public Vec2 getPosition() { return position; }

    public float getRadius() {
        return radius;
    }
}
