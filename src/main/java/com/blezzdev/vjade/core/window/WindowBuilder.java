package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.engine.input.Input;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class WindowBuilder {
    protected Input input = new Input();
    protected long glWindow;

    public WindowBuilder(String name, int width, int height) {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure our window.

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        // Build the window.

        glWindow = glfwCreateWindow(width, height, name, NULL, NULL);
        if (glWindow == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Key callback. (to record inputs)

        glfwSetKeyCallback(glWindow, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        glfwMakeContextCurrent(glWindow); // Make the OpenGL context current
        input.init(glWindow);
    }
}
