package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.elements.build.Root;
import com.blezzdev.vjade.tools.color.Color;
import com.blezzdev.vjade.tools.textures.TextureRenderer;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;

public class WindowRender {
    private TextureRenderer textureRenderer;
    private int fps = 0;

    public void init(Window window, Color color, Root root) {
        GL.createCapabilities();
        glfwSwapInterval(1);

        glEnable(GL_TEXTURE_2D);

        glDisable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glClearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); // Set the clear color.

        textureRenderer = new TextureRenderer();

        // Run the rendering loop until the user has attempted to close.
        // the window or has pressed the ESCAPE key.

        root.start();

        int[] w = new int[1], h = new int[1];
        glfwGetFramebufferSize(window.getGlWindow(), w, h);
        glViewport(0, 0, w[0], h[0]);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0, w[0], h[0], 0, -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        int cycle = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (!glfwWindowShouldClose(window.getGlWindow())) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0; // segundos
            lastTime = now;

            glClear(GL_COLOR_BUFFER_BIT);

            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            root.render();
            root.update(deltaTime);

            cycle++;

            // Poll for window events.
            // The key callback above will only be invoked during this call.

            glfwSwapBuffers(window.getGlWindow());
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
    public TextureRenderer getTextureRenderer() { return textureRenderer; }
}
