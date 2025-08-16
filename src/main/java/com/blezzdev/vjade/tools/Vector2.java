package com.blezzdev.vjade.tools;

public class Vector2 {
    private float x;
    private float y;

    public Vector2(int x, int y) { this((float) x, (float) y); }
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public int getIntX() { return (int) x; }
    public int getIntY() { return (int) y; }

    public static Vector2 ZERO = new Vector2(0, 0);
    public static Vector2 ONE = new Vector2(1, 1);
}
