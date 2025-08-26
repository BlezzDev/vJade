package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.input.Input;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class WindowBuilder {
    protected Input input = new Input();
    protected long glWindow;

    public WindowBuilder(String name, int width, int height) {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize OpenGL");
        }

        // Configure our window.

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        // Build the window.

        glWindow = glfwCreateWindow(width, height, name, NULL, NULL);
        if (glWindow == NULL) {
            throw new WindowBuildException("Failed to create the window");
        }

        glfwMakeContextCurrent(glWindow); // Make the OpenGL context current
        input.init(glWindow);
    }
}
