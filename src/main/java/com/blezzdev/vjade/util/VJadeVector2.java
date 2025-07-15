package com.blezzdev.vjade.util;

public class VJadeVector2 {
    public float x;
    public float y;

    public VJadeVector2() { this(0, 0); }
    public VJadeVector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public VJadeVector2 add(VJadeVector2 vector2) { return new VJadeVector2(x + vector2.x, y + vector2.y); }
    public VJadeVector2 substract(VJadeVector2 vector2) { return new VJadeVector2(x - vector2.x, y - vector2.y); }
    public VJadeVector2 multiply(VJadeVector2 vector2) { return new VJadeVector2(x * vector2.x, y * vector2.y); }
    public VJadeVector2 divide(VJadeVector2 vector2) { return new VJadeVector2(x / vector2.x, y / vector2.y); }

    public VJadeVector2 add(int value) { return new VJadeVector2(x + value, y + value); }
    public VJadeVector2 substract(int value) { return new VJadeVector2(x - value, y - value); }
    public VJadeVector2 multiply(int value) { return new VJadeVector2(x * value, y * value); }
    public VJadeVector2 divide(int value) { return new VJadeVector2(x / value, y / value); }

    public static VJadeVector2 ONE = new VJadeVector2(1, 1);
}
