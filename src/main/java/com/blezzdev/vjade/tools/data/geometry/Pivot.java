package com.blezzdev.vjade.tools.data.geometry;

public class Pivot {
    private float x, y;

    public Pivot() { this(0, 0); }
    public Pivot(float x, float y) {
        setPivot(x, y);
    }

    public Vector2 toVector2() {
        return new Vector2(x, y);
    }

    private float filterValue(float value) {
        if (value >= 0 && value <= 1) {
            return value;
        } else {
            if (value < 0) {
                return 0;
            } else if (value > 1) {
                return 1;
            }
        }
        return 0;
    }

    public void setPivot(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setX(float x) {
        this.x = filterValue(x);
    }

    public void setY(float y) {
        this.y = filterValue(y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
