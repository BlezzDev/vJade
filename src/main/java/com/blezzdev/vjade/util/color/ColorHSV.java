package com.blezzdev.vjade.util.color;

/*
* ColorHSV is a public class for extract colors in HSV format.
*/

public class ColorHSV extends Color {
    public ColorHSV() { this(0, 0, 0, 1); }
    public ColorHSV(float red, float green, float blue) { this(red, green, blue, 1.0f); }
    public ColorHSV(float red, float green, float blue, float alpha) {
        super(red, green, blue, alpha);
    }

    public ColorRGB toRGBColor() {
        int rgb_r = (int) (r * 255), rgb_g = (int) (g * 255), rgb_b = (int) (b * 255);
        return new ColorRGB(rgb_r, rgb_g, rgb_b, getA());
    }

    // Presets colors

    public static ColorHSV TRANSPARENT = new ColorHSV(0.0f, 0.0f, 0.0f, 0.0f);
    public static ColorHSV BLACK = new ColorHSV(0.0f, 0.0f, 0.0f);
    public static ColorHSV WHITE = new ColorHSV(1.0f, 1.0f, 1.0f);
    public static ColorHSV RED = new ColorHSV(1.0f, 0.0f, 0.0f);
    public static ColorHSV BLUE = new ColorHSV(0.0f, 1.0f, 0.0f);
    public static ColorHSV GREEN = new ColorHSV(0.0f, 0.0f, 1.0f);
    public static ColorHSV YELLOW = new ColorHSV(1.0f, 1.0f, 0.0f);
    public static ColorHSV PINK = new ColorHSV(1.0f, 0.0f, 1.0f);
    public static ColorHSV CYAN = new ColorHSV(0.0f, 1.0f, 1.0f);
    public static ColorHSV JADE = new ColorHSV(0.2f, 0.3f, 0.3f);
}
