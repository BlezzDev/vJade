package com.blezzdev.vjade.tools.color;

public class ColorRGB extends Color {

    // This method divides RGB values by 255 (the maximum value of the format)
    // to transform the RGB values into color values readable by OpenGL.

    public ColorRGB(int r, int g, int b, int a) {
        super((float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255);
    }

    public ColorRGB(int r, int g, int b) {
        super( (float)r / 255, (float) g / 255, (float) b / 255);
    }
}
