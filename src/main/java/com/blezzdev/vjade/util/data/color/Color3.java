package com.blezzdev.vjade.util.data.color;

/**
 * The {@code Color3} class is responsible for handle rgb style colors.
 *
 * @since   1.0.0
 * @version 1.0.0
 * @author  Jesus David Alfaro Marenco
 */

public class Color3 {
    private int color;

    public Color3(int color) { set(color); }
    public Color3(float red, float green, float blue) { set(red, green, blue); }

    public float getRed() {
        int r = (this.color >> 16) & 0xFF;
        return r / 255.0f;
    }

    public float getGreen() {
        int g = (this.color >> 8) & 0xFF;
        return g / 255.0f;
    }

    public float getBlue() {
        int b = this.color & 0xFF;
        return b / 255.0f;
    }

    public Color3 set(int color) {
        this.color = color; return this;
    }

    public Color3 set(float red, float green, float blue) {
        red = Math.max(0.0f, Math.min(1.0f, red));
        green = Math.max(0.0f, Math.min(1.0f, green));
        blue = Math.max(0.0f, Math.min(1.0f, blue));

        int r = Math.round(red * 255);
        int g = Math.round(green * 255);
        int b = Math.round(blue * 255);

        this.color = (r << 16) | (g << 8) | b;

        return this;
    }

    public Color3 setRed(float red) {
        red = Math.max(0.0f, Math.min(1.0f, red));
        int r = Math.round(red * 255);

        this.color = (this.color & 0x0000FFFF) | (r << 16);

        return this;
    }

    public Color3 setGreen(float green) {
        green = Math.max(0.0f, Math.min(1.0f, green));
        int g = Math.round(green * 255);

        this.color = (this.color & 0x00FF00FF) | (g << 8);

        return this;
    }

    public Color3 setBlue(float blue) {
        blue = Math.max(0.0f, Math.min(1.0f, blue));
        int b = Math.round(blue * 255);

        this.color = (this.color & 0x00FFFF00) | b;

        return this;
    }

    public int asInteger() { return color; }

    @Override
    public String toString() { return String.valueOf(color); }
}