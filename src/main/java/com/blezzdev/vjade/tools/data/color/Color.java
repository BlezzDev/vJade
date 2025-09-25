package com.blezzdev.vjade.tools.data.color;

public class Color {
    public float r1, g1, b1, a1;

    public Color(float r, float g, float b) { this(r, g, b, 1); }
    public Color(float r, float g, float b, float a) {
        this.r1 = r;
        this.g1 = g;
        this.b1 = b;
        this.a1 = a;
    }

    @Override
    public String toString() {
        return "(" + r1 + ", "
                + g1 + ", "
                + b1 + ", "
                + a1 + ")";
    }
}
