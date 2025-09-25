package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.tools.data.geometry.Vector2;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

public class Monitor {
    private final GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

    public Vector2 getSize() {
        return new Vector2(videoMode.width(), videoMode.height());
    }

    public int getRefreshRate() { return videoMode.refreshRate(); }

    public int getRedBits() {
        return videoMode.redBits();
    }

    public int getGreenBits() {
        return videoMode.greenBits();
    }

    public int getBlueBits() {
        return videoMode.blueBits();
    }
}
