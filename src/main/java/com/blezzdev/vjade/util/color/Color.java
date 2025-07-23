package com.blezzdev.vjade.util.color;

/**
 * It is not recommended to use
 * this class since it works
 * for internal functionalities of the library.
 */

/*
* Color is a private class how a placeholder for colors.
*/

public class Color {
    protected float r, g, b, a;

    public Color(float red, float green, float blue, float alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

    public float getR() { return r; }
    public float getG() { return g; }
    public float getB() { return b; }
    public float getA() { return a; }

    public void setR(float value) { r = value; }
    public void setG(float value) { g = value; }
    public void setB(float value) { b = value; }
    public void setA(float value) { a = value; }
}