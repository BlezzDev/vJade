package com.blezzdev.vjade.util.color;

/*
* ColorHSV is a public class for extract colors in RGB format.
*/

public class ColorRGB extends Color {
    public ColorRGB() { this(0, 0, 0, 1); }
    public ColorRGB(int red, int green, int blue) { this(red, green, blue, 1); }
    public ColorRGB(int red, int green, int blue, float alpha) {
        super(red, green, blue, alpha);
    }

    public ColorHSV toHSVColor() {
        float hsv_r = r / 255, hsv_g = g / 255, hsv_b = b / 255;
        return new ColorHSV(hsv_r, hsv_g, hsv_b, a);
    }
}
