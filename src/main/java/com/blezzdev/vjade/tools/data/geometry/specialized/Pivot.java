package com.blezzdev.vjade.tools.data.geometry.specialized;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public class Pivot {
    private float x, y;

    public Pivot() {
        this(0, 0);
    }

    public Pivot(float x, float y) {
        setPivot(x, y);
    }

    public Vec2 toVector2() {
        return new Vec2(x, y);
    }

    public void setPivot(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setX(float x) {
        this.x = Math.max(0, Math.min(1, x));
    }

    public void setY(float y) {
        this.y = Math.max(0, Math.min(1, y));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}