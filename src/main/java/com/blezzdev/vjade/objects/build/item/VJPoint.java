package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public class VJPoint<T extends VJPoint<T>> extends VJItem {
    private Vec2 position = new Vec2();

    @SuppressWarnings("unchecked")
    public T setPosition(float x, float y) { setPosition(new Vec2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setPosition(Vec2 position) {
        this.position = position;
        return (T) this;
    }

    public Vec2 getPosition() {
        return position;
    }
}
