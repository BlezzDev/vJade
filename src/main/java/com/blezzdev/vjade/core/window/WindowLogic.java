package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.manager.CollisionManager;
import com.blezzdev.vjade.core.manager.TimerManager;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

class WindowLogic {
    private int shaderProgram;

    private final Window<?> window;
    private final CollisionManager collisionManager = new CollisionManager();
    private final TimerManager timerManager = new TimerManager();

    private int fps;

    public WindowLogic(Window<?> window) {
        this.window = window;
    }

    private void enableModernSettings() {
        int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, window.getVertexShader());
        GL20.glCompileShader(vertexShader);

        int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader, window.getFragemtShader());
        GL20.glCompileShader(fragmentShader);

        shaderProgram = GL20.glCreateProgram();
        GL20.glAttachShader(shaderProgram, vertexShader);
        GL20.glAttachShader(shaderProgram, fragmentShader);
        GL20.glLinkProgram(shaderProgram);

        int success = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            System.out.println("Vertex shader error: " + glGetShaderInfoLog(vertexShader));
        }

        success = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            System.out.println("Fragment shader error: " + glGetShaderInfoLog(fragmentShader));
        }

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            System.out.println("Program link error: " + glGetProgramInfoLog(shaderProgram));
        }

        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);

        glUseProgram(shaderProgram);

        fixWindowScale((int) window.getSize().x, (int) window.getSize().y);
        glfwSetFramebufferSizeCallback(window.glWindow, (win, w, h) -> fixWindowScale(w, h));
    }

    private void fixWindowScale(int w, int h) {
        glViewport(0, 0, w, h);

        Matrix4f projection = new Matrix4f().ortho2D(0, w, h, 0);

        int projLoc = GL20.glGetUniformLocation(shaderProgram, "vjProjection");
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        projection.get(fb);
        GL20.glUseProgram(shaderProgram);
        GL20.glUniformMatrix4fv(projLoc, false, fb);

        int modulateLoc = glGetUniformLocation(shaderProgram, "vjModulate");
        glUniform4f(modulateLoc, 1, 1, 1, 1);

        float time = (System.nanoTime() / 1_000_000_000.0f);
        int timeLoc = glGetUniformLocation(shaderProgram, "vjTime");
        glUniform1f(timeLoc, time);
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

        window.getScreenManager().destroy();
        timerManager.clear();

        glfwFreeCallbacks(window.glWindow);
        glfwDestroyWindow(window.glWindow);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public int getFps() {
        return fps;
    }

    public int getShaderProgram() { return shaderProgram; }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }
    public TimerManager getTimerManager() {
        return timerManager;
    }
}