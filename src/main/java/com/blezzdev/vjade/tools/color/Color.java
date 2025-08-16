package com.blezzdev.vjade.tools.color;

public class Color {
    private float red;
    private float green;
    private float blue;
    private float alpha;

    public Color(float r, float g, float b) { this(r, g, b, 0); }
    public Color(float r, float g, float b, float a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    public float getRed() { return red; }
    public float getGreen() { return green; }
    public float getBlue() { return blue; }
    public float getAlpha() { return alpha; }

    public void setRed(float r) { this.red = r; }
    public void setGreen(float g) { this.green = g; }
    public void setBlue(float b) { this.blue = b; }
    public void setAlpha(float a) { this.alpha = a; }

    public static Color BLACK = new Color(0, 0, 0);
    public static Color DARK_GRAY = new Color(0.25f, 0.25f, 0.25f);
    public static Color GRAY = new Color(0.5f, 0.5f, 0.5f);
    public static Color LIGHT_GRAY = new Color(0.75f, 0.75f, 0.75f);
    public static Color WHITE = new Color(1, 1, 1);

    public static Color RED = new Color(1, 0, 0);
    public static Color ORANGE = new Color(1, 0.5f, 0);
    public static Color YELLOW = new Color(1, 1, 0);
    public static Color LIME = new Color(0.5f, 1, 0);
    public static Color GREEN = new Color(0, 1, 0);
    public static Color CYAN = new Color(0, 1, 0.5f);
    public static Color CELESTE = new Color(0, 1, 1);
    public static Color SKY_BLUE = new Color(0, 0.5f, 1);
    public static Color BLUE = new Color(0, 0, 1);
    public static Color PURPLE = new Color(0.5f, 0, 1);
    public static Color MAGENTA = new Color(1, 0, 1);

    public static Color BROWN = new Color(0.25f, 0, 0);
    public static Color PINK = new Color(1, 0.5f, 0.5f);
    public static Color DEEP_PINK = new Color(1, 0, 0.5f);
}
