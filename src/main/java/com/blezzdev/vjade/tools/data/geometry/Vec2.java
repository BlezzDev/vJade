package com.blezzdev.vjade.tools.data.geometry;

/// Vec2 is a mathematical class designed to
/// manage coordinates in two-dimensional planes.
///
/// has 5 methods to modify its values in a more comfortable way:
///
/// - add() Acts as addition.
/// - subtract() Acts as subtraction.
/// - multiply() Acts as a multiplier.
/// - divide() Acts as divisor.
/// - modulate() Acts as modulator.
///
/// If you need to set values in the following way
/// more directly you can use the corresponding setters (setX() and setY()).

public class Vec2 {
    public float x, y;

    public Vec2() { this(0); }

    public Vec2(int i) { this(i, i); }
    public Vec2(int x, int y) { this((float) x, (float) y); }

    public Vec2(Vec2 vector2) { this(vector2.x, vector2.y); }

    public Vec2(float i) { this(i, i); }
    public Vec2(float x, float y) {
        set(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vec2 add(Vec2 vector) {
        this.x += vector.x; this.y += vector.y;
        return this;
    }
    public Vec2 subtract(Vec2 vector) {
        this.x -= vector.x; this.y -= vector.y;
        return this;
    }
    public Vec2 multiply(Vec2 vector) {
        this.x *= vector.x; this.y *= vector.y;
        return this;
    }
    public Vec2 divide(Vec2 vector) {
        this.x /= vector.x; this.y /= vector.y;
        return this;
    }
    public Vec2 modulate(Vec2 vector) {
        this.x %= vector.x; this.y %= vector.y;
        return this;
    }

    public Vec2 add(float x, float y) {
        this.x += x; this.y += y;
        return this;
    }
    public Vec2 subtract(float x, float y) {
        this.x -= x; this.y -= y;
        return this;
    }
    public Vec2 multiply(float x, float y) {
        this.x *= x; this.y *= y;
        return this;
    }
    public Vec2 divide(float x, float y) {
        this.x /= x; this.y /= y;
        return this;
    }
    public Vec2 modulate(float x, float y) {
        this.x %= x; this.y %= y;
        return this;
    }

    public Vec2 add(float i) {
        this.x += i; this.y += i;
        return this;
    }
    public Vec2 subtract(float i) {
        this.x -= i; this.y -= i;
        return this;
    }
    public Vec2 multiply(float i) {
        this.x *= i; this.y *= i;
        return this;
    }
    public Vec2 divide(float i) {
        this.x /= i; this.y /= i;
        return this;
    }
    public Vec2 modulate(float i) {
        this.x %= i; this.y %= i;
        return this;
    }

    public Vec2 set(Vec2 vector2) { set(vector2.x, vector2.y); return this; }
    public Vec2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2 setX(float x) {
        this.x = x;
        return this;
    }

    public Vec2 setY(float y) {
        this.y = y;
        return this;
    }
}
