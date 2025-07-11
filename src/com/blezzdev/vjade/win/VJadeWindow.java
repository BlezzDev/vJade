package com.blezzdev.vjade.win;

import com.blezzdev.vjade.util.color.VJadeColorHSV;
import com.blezzdev.vjade.win.low.VJCreateWin;
import com.blezzdev.vjade.win.low.VJLoopWin;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class VJadeWindow {
    public VJadeColorHSV backgroundColor = new VJadeColorHSV(0.2f, 0.3f, 0.3f);

    private long window;
    public VJadeWindow() { this(800, 600, "vJade"); }
    public VJadeWindow(int width, int height, String title) { initWindow(width, height, title); }

    private void initWindow(int width, int height, String title) {
        window = new VJCreateWin().create(width, height, title);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }

    public void display() {
        glfwShowWindow(window);
        new VJLoopWin().looping(window, backgroundColor);
    }

    // Window getters
}
