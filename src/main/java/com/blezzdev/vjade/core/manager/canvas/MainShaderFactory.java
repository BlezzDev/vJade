package com.blezzdev.vjade.core.manager.canvas;

import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.util.canvas.Shader;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback;
import static org.lwjgl.opengl.GL11C.glViewport;

public class MainShaderFactory {
    public static Shader build() {
        return configureMainShader();
    }

    private static Shader configureMainShader() {
        Shader mainShader = new Shader();
        mainShader.load().bind();
        return mainShader;
    }

    public static void bindWindowScale(Shader mainShader, Window window) {
        fixWindowScale(mainShader, window.getProperties().getWidth(), window.getProperties().getHeight());
        glfwSetFramebufferSizeCallback(window.asLong(), (win, w, h) -> fixWindowScale(mainShader, w, h));
    }

    private static void fixWindowScale(Shader mainShader, int w, int h) {
        glViewport(0, 0, w, h);

        Matrix4f projection = new Matrix4f().ortho2D(0, w, h, 0);
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        projection.get(fb);

        mainShader.bind();
        mainShader.setUniformMatrix4("vProjection", fb);

        float time = (System.nanoTime() / 1_000_000_000.0f);
        mainShader.setUniformFloat("vTime", time);
    }
}