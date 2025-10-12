package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.objects.build.item.RectItem;
import com.blezzdev.vjade.tools.data.geometry.Vector2;
import com.blezzdev.vjade.util.types.Shapes;

public class RectangleCollider extends Collider implements RectItem<RectangleCollider> {
    public RectangleCollider() { this(0, 0, 0, 0); }
    public RectangleCollider(float x, float y, float w, float h) {
        this(new Vector2(x, y), new Vector2(w, h));
    }
    public RectangleCollider(Vector2 position, Vector2 size) {
        super(Shapes.RECTANGLE);

        setPosition(position);
        setSize(size);
    }
}