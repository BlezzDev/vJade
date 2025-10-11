package com.blezzdev.vjade.core.window;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.opengl.GL;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

class WindowBuilder {
    private long device;
    private long context;
    private String vertexShader;
    private String fragemtShader;
    protected long glWindow;

    public WindowBuilder() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize OpenGL.");
        }

        // Configure our window.

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        // Build the window.

        glWindow = glfwCreateWindow(800, 600, "vJade window.", NULL, NULL);
        glfwSetWindowPos(glWindow, 0, 30);

        glfwMakeContextCurrent(glWindow);
        GL.createCapabilities();

        device = ALC10.alcOpenDevice((ByteBuffer) null);
        if (device == NULL) throw new IllegalStateException("Failed to open audio device.");

        context = ALC10.alcCreateContext(device, (IntBuffer) null);
        if (context == NULL) throw new IllegalStateException("Failed to create OpenAL context.");

        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(ALC.createCapabilities(device));

        glfwMakeContextCurrent(glWindow);
        GL.createCapabilities();
    }

    public void setFragemtShader(String fragemtShader) {
        this.fragemtShader = fragemtShader;
    }

    public void setVertexShader(String vertexShader) {
        this.vertexShader = vertexShader;
    }

    public String getVertexShader() {
        return vertexShader;
    }

    public String getFragemtShader() {
        return fragemtShader;
    }

    public long getSoundDevice() {
        return device;
    }

    public long getSoundContext() {
        return context;
    }
}
