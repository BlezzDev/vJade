package com.blezzdev.vjade.tools.data.geometry;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() { this(0, 0); }
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vector2 add(float x, float y) {
        this.x += x; this.y += y;
        return this;
    }

    public Vector2 subtract(float x, float y) {
        this.x -= x; this.y -= y;
        return this;
    }

    public Vector2 multiply(float x, float y) {
        this.x *= x; this.y *= y;
        return this;
    }

    public Vector2 divide(float x, float y) {
        this.x /= x; this.y /= y;
        return this;
    }

    public Vector2 modulate(float x, float y) {
        this.x %= x; this.y %= y;
        return this;
    }

    public Vector2 setX(float x) {
        this.x = x;
        return this;
    }

    public Vector2 setY(float y) {
        this.y = y;
        return this;
    }
}
