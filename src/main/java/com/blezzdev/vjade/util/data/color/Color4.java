package com.blezzdev.vjade.util.data.color;

/**
 * The {@code Color4} class is responsible for handle rgba style colors.
 *
 * @since   1.0.0
 * @version 1.0.0
 * @author  Jesus David Alfaro Marenco
 */
public class Color4 {
    private int color;

    public Color4(int color) {
        set(color);
    }

    public Color4(float red, float green, float blue, float alpha) {
        set(red, green, blue, alpha);
    }

    public float getAlpha() {
        int a = (this.color >> 24) & 0xFF;
        return a / 255.0f;
    }

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

    public Color4 set(int color) {
        this.color = color;
        return this;
    }

    public Color4 set(float red, float green, float blue, float alpha) {
        red = Math.max(0.0f, Math.min(1.0f, red));
        green = Math.max(0.0f, Math.min(1.0f, green));
        blue = Math.max(0.0f, Math.min(1.0f, blue));
        alpha = Math.max(0.0f, Math.min(1.0f, alpha));

        int a = Math.round(alpha * 255);
        int r = Math.round(red * 255);
        int g = Math.round(green * 255);
        int b = Math.round(blue * 255);

        this.color = (a << 24) | (r << 16) | (g << 8) | b;

        return this;
    }

    public Color4 setAlpha(float alpha) {
        alpha = Math.max(0.0f, Math.min(1.0f, alpha));
        int a = Math.round(alpha * 255);

        // Limpia el canal alfa antiguo (0x00FFFFFF) y aplica el nuevo
        this.color = (this.color & 0x00FFFFFF) | (a << 24);

        return this;
    }

    public Color4 setRed(float red) {
        red = Math.max(0.0f, Math.min(1.0f, red));
        int r = Math.round(red * 255);

        // Conserva Alfa (0xFF), Verde y Azul (0xFFFF)
        this.color = (this.color & 0xFF00FFFF) | (r << 16);

        return this;
    }

    public Color4 setGreen(float green) {
        green = Math.max(0.0f, Math.min(1.0f, green));
        int g = Math.round(green * 255);

        // Conserva Alfa y Rojo (0xFFFF), y Azul (0xFF)
        this.color = (this.color & 0xFFFF00FF) | (g << 8);

        return this;
    }

    public Color4 setBlue(float blue) {
        blue = Math.max(0.0f, Math.min(1.0f, blue));
        int b = Math.round(blue * 255);

        // Conserva Alfa, Rojo y Verde (0xFFFFFF00)
        this.color = (this.color & 0xFFFFFF00) | b;

        return this;
    }

    public int asInteger() {
        return color;
    }

    @Override
    public String toString() {
        return String.valueOf(color);
    }
}