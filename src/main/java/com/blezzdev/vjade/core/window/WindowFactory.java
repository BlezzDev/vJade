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

/**
 * A factory responsible for the initialization, configuration, and centralized
 * creation of the {@link Window}, the graphics rendering
 * context (OpenGL) and the audio system context (OpenAL).
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */
public class WindowFactory {

    /**
     * Builds and fully initializes the application environment.
     * <p>
     * This method sequentially performs the following GLFW error configuration
     * steps:
     * </p>
     *
     * <p>
     * initializing the window library and setting properties (hints),
     * creating the native window and enabling OpenGL features
     * (including V-Sync), and opening the audio device and context with
     * OpenAL.
     * </p>
     *
     * @param title the title that will appear in the top bar of the window.
     * @param width the initial window width in pixels.
     * @param height the initial window height in pixels.
     * @return a new {@link Window} instance that encapsule the created native
     * pointers.
     * @throws IllegalStateException if GLFW can't initialize, if the audio
     * device can't open or if the OpenAL context creation fail.
     */
    public static Window build(String title, int width, int height) {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        configureHints();

        long window = glfwCreateWindow(width, height, title, NULL, NULL);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // new OpenGLDebugMode(); // Enable openGL debug mode

        long device = createDevice();
        long context = createContext(device);

        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(ALC.createCapabilities(device));

        return new Window(window, device, context, title, width, height);
    }

    // Create the OpenAL context as long.
    private static long createContext(long device) {
        long context = ALC10.alcCreateContext(device, (IntBuffer) null);
        if (context == NULL) throw new IllegalStateException("Failed to create OpenAL context");
        return context;
    }

    // Create the OpenAL audio device as long.
    private static long createDevice() {
        long device = ALC10.alcOpenDevice((ByteBuffer) null);

        if (device == NULL) throw new IllegalStateException("Failed to open audio device");
        return device;
    }

    // Configure default hints of the window.
    private static void configureHints() {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);

        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_FALSE);

        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
    }
}