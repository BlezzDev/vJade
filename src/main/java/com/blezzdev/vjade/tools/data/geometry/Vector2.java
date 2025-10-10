package com.blezzdev.vjade.tools.data.geometry;

/// Vector2 is a mathematical class designed to
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
