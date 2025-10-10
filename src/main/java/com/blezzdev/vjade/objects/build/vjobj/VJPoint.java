package com.blezzdev.vjade.objects.build.vjobj;

import com.blezzdev.vjade.tools.data.geometry.Vector2;

public class VJPoint<T extends VJPoint<T>> extends VJObject {
    private Vector2 position = new Vector2();

    @SuppressWarnings("unchecked")
    public T setPosition(float x, float y) { setPosition(new Vector2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setPosition(Vector2 position) {
        this.position = position;
        return (T) this;
    }

    public Vector2 getPosition() {
        return position;
    }
}
