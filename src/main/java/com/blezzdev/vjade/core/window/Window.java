package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.tools.Vector2;
import com.blezzdev.vjade.tools.color.Color;

import static org.lwjgl.glfw.GLFW.*;

public class Window extends WindowBuilder {
    private Vector2 size = Vector2.ZERO;
    private String title = "";
    private boolean decorated = true;
    private boolean resizable = false;
    private Color bgColor = new Color(0, 0, 0); // Starts with a black background.

    public Window() { this("", 800, 600); }
    public Window(String name, int width, int height) {
        super(name, width, height);

        this.title = name;
        this.size.setX(width).setY(height);
    }

    // Local method to transform an integer value into its boolean variant.

    private int boolToInt(boolean bool) {
        if (bool) { return 1; }
        else { return 0; }
    }

    protected Color getBGColor() { return bgColor; }
    public long getGlWindow() { return glWindow; }

    public void setTitle(String title) {
        glfwSetWindowTitle(glWindow, title);
        this.title = title;
    }

    public void setSize(Vector2 size) {
        setSize(size.getIntX(), size.getIntY());
    }

    public void setSize(int width, int height) {
        glfwSetWindowSize(glWindow, width, height);
        this.size = new Vector2(width, height);
    }

    public void setDecorations(boolean value) {
        glfwSetWindowAttrib(glWindow, GLFW_DECORATED, boolToInt(value));
        this.decorated = value;
    }

    public void setResizable(boolean value) {
        glfwSetWindowAttrib(glWindow, GLFW_RESIZABLE, boolToInt(value));
        this.resizable = value;
    }

    public void setBackgroundColor(Color color) {
        this.bgColor = color;
    }

    public String getTitle() {
        return title;
    }

    public Vector2 getSize() {
        return size;
    }

    public Color getBackgroundColor() {
        return bgColor;
    }

    public boolean isDecorated() {
        return decorated;
    }

    public boolean isResizable() {
        return resizable;
    }
}