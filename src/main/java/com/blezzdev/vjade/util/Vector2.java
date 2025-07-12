package com.blezzdev.vjade.util;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() { this(0, 0); }
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 vector2) { return new Vector2(x + vector2.x, y + vector2.y); }
    public Vector2 substract(Vector2 vector2) { return new Vector2(x - vector2.x, y - vector2.y); }
    public Vector2 multiply(Vector2 vector2) { return new Vector2(x * vector2.x, y * vector2.y); }
    public Vector2 divide(Vector2 vector2) { return new Vector2(x / vector2.x, y / vector2.y); }

    public Vector2 add(int value) { return new Vector2(x + value, y + value); }
    public Vector2 substract(int value) { return new Vector2(x - value, y - value); }
    public Vector2 multiply(int value) { return new Vector2(x * value, y * value); }
    public Vector2 divide(int value) { return new Vector2(x / value, y / value); }

    public static Vector2 ONE = new Vector2(1, 1);
}
