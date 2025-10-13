package com.blezzdev.vjade.tools.data.geometry;

/// Vec2 is a mathematical class designed to
/// manage coordinates in three-dimensional spaces.
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

public class Vec3 extends Vec2 {
    public float z;

    public Vec3() { this(0); }
    public Vec3(float i) { this(i, i, i); }
    public Vec3(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Vec3 add(float x, float y, float z) {
        this.x += x; this.y += y; this.z += z;
        return this;
    }

    public Vec3 subtract(float x, float y, float z) {
        this.x -= x; this.y -= y; this.z -= z;
        return this;
    }

    public Vec3 multiply(float x, float y, float z) {
        this.x *= x; this.y *= y; this.z *= z;
        return this;
    }

    public Vec3 divide(float x, float y, float z) {
        this.x /= x; this.y /= y; this.z /= z;
        return this;
    }

    public Vec3 modulate(float x, float y, float z) {
        this.x %= x; this.y %= y; this.z %= z;
        return this;
    }

    public Vec3 setX(float x) {
        this.x = x;
        return this;
    }

    public Vec3 setY(float y) {
        this.y = y;
        return this;
    }

    public Vec3 setZ(float z) {
        this.z = z;
        return this;
    }
}
