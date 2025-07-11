package com.blezzdev.vjade.util.color;

public class VJadeColorHSV extends VJadeColor {
    public VJadeColorHSV() { this(0, 0, 0, 1); }
    public VJadeColorHSV(float red, float green, float blue) { this(red, green, blue, 1.0f); }
    public VJadeColorHSV(float red, float green, float blue, float alpha) {
        super(red, green, blue, alpha);
    }

    public VJadeColorRGB toRGBColor() {
        int rgb_r = (int) (r * 255), rgb_g = (int) (g * 255), rgb_b = (int) (b * 255);
        return new VJadeColorRGB(rgb_r, rgb_g, rgb_b, getA());
    }

    // Presets colors

    public static VJadeColorHSV TRANSPARENT = new VJadeColorHSV(0.0f, 0.0f, 0.0f, 0.0f);
    public static VJadeColorHSV BLACK = new VJadeColorHSV(0.0f, 0.0f, 0.0f);
    public static VJadeColorHSV WHITE = new VJadeColorHSV(1.0f, 1.0f, 1.0f);
    public static VJadeColorHSV RED = new VJadeColorHSV(1.0f, 0.0f, 0.0f);
    public static VJadeColorHSV BLUE = new VJadeColorHSV(0.0f, 1.0f, 0.0f);
    public static VJadeColorHSV GREEN = new VJadeColorHSV(0.0f, 0.0f, 1.0f);
    public static VJadeColorHSV YELLOW = new VJadeColorHSV(1.0f, 1.0f, 0.0f);
    public static VJadeColorHSV PINK = new VJadeColorHSV(1.0f, 0.0f, 1.0f);
    public static VJadeColorHSV CELESTE = new VJadeColorHSV(0.0f, 1.0f, 1.0f);
    public static VJadeColorHSV JADE = new VJadeColorHSV(0.2f, 0.3f, 0.3f);
}
