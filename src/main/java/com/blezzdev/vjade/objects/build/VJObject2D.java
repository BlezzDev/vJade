package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.tools.data.geometry.Vector2;

public class VJObject2D<T extends VJObject2D<T>> extends VJPoint<T> {
    private Vector2 size = new Vector2(1, 1);

    @SuppressWarnings("unchecked")
    public T setSize(float width, float height) { setSize(new Vector2(width, height)); return (T) this;}
    @SuppressWarnings("unchecked")
    public T setSize(Vector2 size) {
        this.size = size;
        return (T) this;
    }

    public Vector2 getSize() {
        return size;
    }
}
