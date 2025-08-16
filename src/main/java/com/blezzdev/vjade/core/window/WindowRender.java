package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.tools.color.Color;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;

public class WindowRender {
    public WindowRender(long glWindow, Color color) {
        GL.createCapabilities();

        glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); // Set the clear color.

        // Run the rendering loop until the user has attempted to close.
        // the window or has pressed the ESCAPE key.

        while (!glfwWindowShouldClose(glWindow)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glWindow); // swap the color buffers.

            // Poll for window events.
            // The key callback above will only be invoked during this call.

            glfwPollEvents();
        }
    }
}
