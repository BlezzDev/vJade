package com.blezzdev.vjade.tools.data.color;

/// Color is a class designed to handle rgba style colors.
///
/// you can create the color from the rgba model with a range from 0.00 to 1.00.

public class Color {
    private float r1, g1, b1, a1;

    public Color(float r, float g, float b) {
        setColor(r, g, b);
    }
    public Color(float r, float g, float b, float a) {
        setColor(r, g, b, a);
    }

    @Override
    public String toString() {
        return "(" + r1 + ", "
                + g1 + ", "
                + b1 + ", "
                + a1 + ")";
    }

    private float verifyValue(float value) {
        if (value >= 0 && value <= 1) {
            return value;
        } else {
             if (value < 0) {
                 return 0;
             } else if (value > 1) {
                 return 1;
             }
        }
        return 0;
    }

    public Color setColor(float red, float green, float blue) {
        setColor(red, green, blue, 1);
        return this;
    }
    public Color setColor(float red, float green, float blue, float alpha) {
        setRed(red).setGreen(green).setBlue(blue).setAlpha(alpha);
        return this;
    }

    public Color setRed(float red) {
        this.r1 = verifyValue(red);
        return this;
    }

    public Color setGreen(float green) {
        this.g1 = verifyValue(green);
        return this;
    }

    public Color setBlue(float blue) {
        this.b1 = verifyValue(blue);
        return this;
    }

    public Color setAlpha(float alpha) {
        this.a1 = verifyValue(alpha);
        return this;
    }

    public float getRed() {
        return r1;
    }

    public float getGreen() {
        return g1;
    }

    public float getBlue() {
        return b1;
    }

    public float getAlpha() {
        return a1;
    }
}
