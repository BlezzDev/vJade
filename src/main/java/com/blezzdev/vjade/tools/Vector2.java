package com.blezzdev.vjade.tools;

public class Vector2 {

    private float x;
    private float y;

    public Vector2() { this(0, 0); }
    public Vector2(int x, int y) { this((float) x, (float) y); }
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public int getIntX() { return (int) x; }
    public int getIntY() { return (int) y; }

    public void setX(float value) { this.x = value; }
    public void setY(float value) { this.y = value; }

    public void addX(float value) { this.x = this.x + value; }
    public void addY(float value) { this.y = this.y + value; }

    public Vector2 add(Vector2 vector2) { this.x += vector2.x; this.y += vector2.y; return this; }
    public Vector2 substract(Vector2 vector2) { this.x -= vector2.x; this.y -= vector2.y; return this; }
    public Vector2 multiply(Vector2 vector2) { this.x *= vector2.x; this.y *= vector2.y; return this; }
    public Vector2 divide(Vector2 vector2) { this.x /= vector2.x; this.y /= vector2.y; return this; }

    public Vector2 add(float value) { this.x += value; this.y += value; return this; }
    public Vector2 substract(float value) { this.x -= value; this.y -= value; return this; }
    public Vector2 multiply(float value) { this.x *= value; this.y *= value; return this; }
    public Vector2 divide(float value) { this.x /= value; this.y /= value; return this; }

    public static Vector2 ZERO = new Vector2(0, 0);
    public static Vector2 ONE = new Vector2(1, 1);
}
