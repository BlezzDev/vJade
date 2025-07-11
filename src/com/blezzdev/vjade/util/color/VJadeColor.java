package com.blezzdev.vjade.util.color;

public class VJadeColor {
    protected float r, g, b, a;

    public VJadeColor(float red, float green, float blue, float alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

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