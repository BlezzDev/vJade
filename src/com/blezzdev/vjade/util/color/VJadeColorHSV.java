package com.blezzdev.vjade.util.color;

public class VJadeColorHSV {
    private float r, g, b, a;

    public VJadeColorHSV() { this(0, 0, 0, 1); }
    public VJadeColorHSV(float red, float green, float blue) { this(red, green, blue, 1); }
    public VJadeColorHSV(float red, float green, float blue, float alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

    public VJadeColorRGB toRGBColor() {
        int rgb_r = (int) (r * 255), rgb_g = (int) (g * 255), rgb_b = (int) (b * 255);
        return new VJadeColorRGB(rgb_r, rgb_g, rgb_b, a);
    }

    // VJadeColor getters

    public float getR() { return r; }
    public float getG() { return g; }
    public float getB() { return b; }
    public float getA() { return a; }

    // VJadeColor setters

    public void setR(float value) { r = value; }
    public void setG(float value) { g = value; }
    public void setB(float value) { b = value; }
    public void setA(float value) { a = value; }
}
