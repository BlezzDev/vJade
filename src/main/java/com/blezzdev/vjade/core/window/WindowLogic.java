package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.manager.CollisionManager;
import com.blezzdev.vjade.core.manager.TimerManager;
import com.blezzdev.vjade.objects.build.Shader;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

class WindowLogic {
    private Shader shader;

    private final Window<?> window;
    private final CollisionManager collisionManager = new CollisionManager();
    private final TimerManager timerManager = new TimerManager();

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
        shader.setUniform(Shader.Uniform.MATRIX_4, "vjProjection", fb);
        shader.setUniform(Shader.Uniform.FLOAT_4, "vjModulate", 1, 1, 1, 1);

        float time = (System.nanoTime() / 1_000_000_000.0f);
        shader.setUniform(Shader.Uniform.FLOAT_1, "vjTime", time);
    }

    public void init() {
        enableModernSettings();

        window.getScreenManager().init();

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

            timerManager.timerProcesses(deltaTime); // Run time processes.
            window.getScreenManager().screenLifeCycle(deltaTime); // Run screen processes.

            window.getInput().update();

            cycle++;

            // Poll for window events.
            // The key callback above will only be invoked during this call.

            glfwSwapBuffers(window.glWindow);
            glfwPollEvents();
        }

        shader.cleanup();

        window.getScreenManager().destroy();
        timerManager.clear();

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

    public void bindShader() {
        if (shader != null) {
            shader.bind();
        }
    }

    public int getFps() {
        return fps;
    }

    public Shader getShader() { return shader; }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
    public TimerManager getTimerManager() {
        return timerManager;
    }
}