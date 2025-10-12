package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.objects.build.item.PointItem;
import com.blezzdev.vjade.tools.data.geometry.Vector2;
import com.blezzdev.vjade.util.types.Shapes;

public class CircleCollider extends Collider implements PointItem<CircleCollider> {
    public float radius;

    public CircleCollider() { this(0, 0, 0); }
    public CircleCollider(float x, float y, float radius) { this(new Vector2(x, y), radius); }
    public CircleCollider(Vector2 position, float radius) {
        super(Shapes.CIRCLE);

        setPosition(position);
        this.radius = radius;
    }

    public CircleCollider setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public float getRadius() {
        return radius;
    }
}
