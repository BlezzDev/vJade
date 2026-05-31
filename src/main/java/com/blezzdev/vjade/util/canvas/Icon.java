package com.blezzdev.vjade.util.canvas;

import static org.lwjgl.glfw.GLFW.glfwSetWindowIcon;

public class Icon extends ImageLoader {
    public Icon(String resourcePath) {
        super(resourcePath);
    }

    public void applyToWindow(long windowHandle) {
        if (iconBuffer != null) glfwSetWindowIcon(windowHandle, iconBuffer);
        else System.err.println("The icon is not loaded before applying.");
    }
}