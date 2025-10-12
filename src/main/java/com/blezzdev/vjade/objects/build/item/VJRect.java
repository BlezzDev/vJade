package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public class VJRect<T extends VJRect<T>> extends VJPoint<T> {
    private Vec2 size = new Vec2(1, 1);

    @SuppressWarnings("unchecked")
    public T setSize(float x, float y) { setSize(new Vec2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setSize(Vec2 size) {
        this.size = size;
        return (T) this;
    }

    public Vec2 getSize() {
        return size;
    }
}
