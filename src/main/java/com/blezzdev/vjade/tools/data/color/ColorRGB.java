package com.blezzdev.vjade.tools.data.color;

public class ColorRGB extends Color {
    public int red, green, blue, alpha;

    public ColorRGB(int red, int green, int blue) { this(red, green, blue, 255); }
    public ColorRGB(int red, int green, int blue, int alpha) {
        super((float) red / 255f, (float) green / 255f, (float) blue / 255f, (float) alpha / 255f);

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public String toString() {
        return "(" + red + ", "
                + green + ", "
                + blue + ", "
                + alpha + ")";
    }
}
