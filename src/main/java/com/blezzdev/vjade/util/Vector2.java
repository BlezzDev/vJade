package com.blezzdev.vjade.util;

/*
* Vector2 is a public class for calculate positions, sizes and similar.
*/

public class Vector2 {
    public float x;
    public float y;
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 vector2) { return new Vector2(x + vector2.x, y + vector2.y); }
    public Vector2 substract(Vector2 vector2) { return new Vector2(x - vector2.x, y - vector2.y); }
    public Vector2 multiply(Vector2 vector2) { return new Vector2(x * vector2.x, y * vector2.y); }
    public Vector2 divide(Vector2 vector2) { return new Vector2(x / vector2.x, y / vector2.y); }

    public Vector2 add(float value) { return new Vector2(x + value, y + value); }
    public Vector2 substract(float value) { return new Vector2(x - value, y - value); }
    public Vector2 multiply(float value) { return new Vector2(x * value, y * value); }
    public Vector2 divide(float value) { return new Vector2(x / value, y / value); }

    public static Vector2 ONE = new Vector2(1, 1);
    public static Vector2 ZERO = new Vector2(0, 0);
}
