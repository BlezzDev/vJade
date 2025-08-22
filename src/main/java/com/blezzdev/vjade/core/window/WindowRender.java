package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.elements.basic.Root;
import com.blezzdev.vjade.tools.color.Color;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;

public class WindowRender {
    private int fps = 0;

    public WindowRender(long glWindow, Color color, Root root) {
        GL.createCapabilities();

        glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); // Set the clear color.

        // Run the rendering loop until the user has attempted to close.
        // the window or has pressed the ESCAPE key.

        root.start();

        int cycle = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (!glfwWindowShouldClose(glWindow)) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0; // segundos
            lastTime = now;

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glWindow); // swap the color buffers.

            // Poll for window events.
            // The key callback above will only be invoked during this call.

            cycle++;

            root.render();
            root.update(deltaTime, fps);

            glfwPollEvents();

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                this.fps = cycle;
                cycle = 0;
            }
        }

        root.end();
    }

    public int getFps() { return fps; }
}
