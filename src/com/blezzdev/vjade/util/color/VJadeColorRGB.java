package com.blezzdev.vjade.util.color;

public class VJadeColorRGB {
    private int r, g, b;
    private float a;

    public VJadeColorRGB() { this(0, 0, 0, 1); }
    public VJadeColorRGB(int red, int green, int blue) { this(red, green, blue, 1); }
    public VJadeColorRGB(int red, int green, int blue, float alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

    public VJadeColorHSV toHSVColor() {
        float hsv_r = (float) r / 255, hsv_g = (float) g / 255, hsv_b = (float) b / 255;
        return new VJadeColorHSV(hsv_r, hsv_g, hsv_b, a);
    }

    // VJadeColor getters

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public float getA() { return a; }

    // VJadeColor setters

    public void setR(int value) { r = value; }
    public void setG(int value) { g = value; }
    public void setB(int value) { b = value; }
    public void setA(float value) { a = value; }
}
