package com.blezzdev.vjade.tools.data.geometry;

public class Pivot {
    private float x, y;

    public Pivot() { this(0, 0); }
    public Pivot(float x, float y) {
        setPivot(x, y);
    }

    public Vector2 vanillaFormat() {
        return new Vector2(x, y);
    }

    private float filterValue(float value) {
        if (value >= 0 && value <= 1) {
            return (value - 1) * -1;
        } else {
            if (value < 0) {
                return 1;
            } else if (value > 1) {
                return 0;
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
        return (x - 1) * -1;
    }

    public float getY() {
        return (x - 1) * -1;
    }
}
