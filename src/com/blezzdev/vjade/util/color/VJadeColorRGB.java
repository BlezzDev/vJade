package com.blezzdev.vjade.util.color;

public class VJadeColorRGB extends VJadeColor {
    public VJadeColorRGB() { this(0, 0, 0, 1); }
    public VJadeColorRGB(int red, int green, int blue) { this(red, green, blue, 1); }
    public VJadeColorRGB(int red, int green, int blue, float alpha) {
        super(red, green, blue, alpha);
    }

    public VJadeColorHSV toHSVColor() {
        float hsv_r = r / 255, hsv_g = g / 255, hsv_b = b / 255;
        return new VJadeColorHSV(hsv_r, hsv_g, hsv_b, a);
    }
}
