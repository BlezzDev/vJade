package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.tools.canvas.Shader;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.openal.ALC10.alcCloseDevice;
import static org.lwjgl.openal.ALC10.alcDestroyContext;
import static org.lwjgl.opengl.GL11.*;

class WindowLogic {
    private Shader shader;

    private boolean shutdown = false;

    private final Window<?> window;

    private int fps;

    public WindowLogic(Window<?> window) {
        this.window = window;
    }

    private void enableModernSettings() {
        shader = new Shader();
        shader.setVertexShader(window.getVertexShader());
        shader.setFragmentShader(window.getFragemtShader());
        shader.load();

        shader.bind();

        fixWindowScale((int) window.getSize().x, (int) window.getSize().y);
        glfwSetFramebufferSizeCallback(window.glWindow, (win, w, h) -> fixWindowScale(w, h));
    }

    private void fixWindowScale(int w, int h) {
        glViewport(0, 0, w, h);

        Matrix4f projection = new Matrix4f().ortho2D(0, w, h, 0);
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        projection.get(fb);

        shader.bind();
        shader.setUniformMatrix4("vProjection", fb);

        float time = (System.nanoTime() / 1_000_000_000.0f);
        shader.setUniformFloat("vTime", time);
    }

    public void init() {
        enableModernSettings();

        window.getManagers().start();

        glfwShowWindow(window.glWindow);

        int cycle = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (!glfwWindowShouldClose(window.glWindow)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Window data.

            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                this.fps = cycle;
                cycle = 0;
            }

            // Main process.

            /*

            ¡ DESEÁNDOTE,
            CADA DIA, CADA NOCHE, DESEÁNDOTE,
            PARA HUNDIRME EN TUS ABISMOS, INVENTÁNDOTE,
            CUANDO TIEMBLO Y ME DERRAMO SOBRE ELLA !

            PD: i hate shaders.

            */

            window.getManagers().update(deltaTime);

            cycle++;

            // Poll for window events.
            // The key callback above will only be invoked during this call.

            glfwSwapBuffers(window.glWindow);
            glfwPollEvents();

            if (shutdown) { break; }
        }

        shader.cleanup();

        window.getManagers().end();

        alcDestroyContext(window.getSoundContext());
        alcCloseDevice(window.getSoundDevice());

        glfwFreeCallbacks(window.glWindow);
        glfwDestroyWindow(window.glWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void setShader(Shader newShader) {
        if (this.shader != null) {
            this.shader.cleanup();
        }
        this.shader = newShader;
        if (this.shader != null) {
            this.shader.bind();
            fixWindowScale((int) window.getSize().x, (int) window.getSize().y);
        }
    }

    public void shutdown() {
        this.shutdown = true;
    }

    public void bindShader() {
        if (shader != null) {
            shader.bind();
        }
    }

    public int getFps() {
        return fps;
    }

    public Shader getShader() { return shader; }
}