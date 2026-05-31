package com.blezzdev.vjade.core.gameloop;

import com.blezzdev.vjade.GameApplication;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.openal.ALC10;
import org.lwjgl.opengl.GL11C;

import static org.lwjgl.opengl.GL11C.*;

/**
 * Controls the main execution lifecycle of a {@link GameApplication}.
 *
 * <p>The {@code GameApplicationLoop} class is responsible for preparing the
 * window before the game loop starts, executing the continuous update/render
 * cycle while the application is running, and releasing native resources when
 * the application finishes.</p>
 *
 * <p>This class manages the following lifecycle phases:</p>
 *
 * <ul>
 *     <li>{@link #preLoop()} prepares the window and rendering state.</li>
 *     <li>{@link #inLoop()} starts managers and runs the main game loop.</li>
 *     <li>{@link #postLoop()} destroys native resources and terminates GLFW.</li>
 *     <li>{@link #breakInLoop()} requests the loop to stop.</li>
 * </ul>
 *
 * <p>The loop uses GLFW for window handling, OpenGL for rendering operations,
 * and OpenAL for audio context cleanup.</p>
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */
public class GameApplicationLoop extends GameAppLoopBase {

    /**
     * Creates a new game application loop for the given application.
     *
     * @param application the game application controlled by this loop
     */
    public GameApplicationLoop(GameApplication application) {
        super(application);
    }

    /**
     * Prepares the application before entering the main loop.
     *
     * <p>This method configures the window behavior, prepares the OpenGL clear
     * color, and makes the application window visible.</p>
     */
    public void preLoop() {
        configureWindowBehaviour();
        prepareClearColor();

        GLFW.glfwShowWindow(application.getWindow().asLong());
        application.getManagers().start();
    }

    /**
     * Executes the main application loop.
     *
     * <p>This method starts all registered managers and continuously performs
     * the render-update cycle until the window receives a close request.</p>
     *
     * <p>During each loop iteration, this method clears the color and depth
     * buffers, updates the registered managers, swaps the window buffers, and
     * polls window events.</p>
     *
     * <p>When the loop ends, all registered managers are finalized through their
     * ending lifecycle method.</p>
     */
    public void inLoop() {
        while (!GLFW.glfwWindowShouldClose(application.getWindow().asLong())) {
            GL11C.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Functional devices

            GLFW.glfwPollEvents();
            application.getManagers().update();
            application.getManagers().postUpdate();

            GLFW.glfwSwapBuffers(application.getWindow().asLong());
        }
    }

    /**
     * Releases the resources used by the application after the loop finishes.
     *
     * <p>This method destroys the window icon, releases the OpenAL audio context
     * and device, frees GLFW callbacks, destroys the application window,
     * terminates GLFW, and clears the GLFW error callback.</p>
     */
    public void postLoop() {
        application.getWindow().getProperties().getIcon().destroy();
        application.getManagers().end();

        ALC10.alcDestroyContext(application.getWindow().getContext());
        ALC10.alcCloseDevice(application.getWindow().getDevice());

        Callbacks.glfwFreeCallbacks(application.getWindow().asLong());
        GLFW.glfwDestroyWindow(application.getWindow().asLong());

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    /**
     * Requests the application loop to stop.
     *
     * <p>This method marks the window as ready to close, causing the main loop
     * in {@link #inLoop()} to finish during its next condition check.</p>
     */
    public void breakInLoop() {
        GLFW.glfwSetWindowShouldClose(application.getWindow().asLong(), true);
    }
}