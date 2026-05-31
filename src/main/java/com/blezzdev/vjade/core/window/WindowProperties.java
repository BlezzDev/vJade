package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.util.canvas.Icon;
import com.blezzdev.vjade.util.data.color.Color3;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Manages the runtime properties and attributes belonging to the
 * {@link Window} class. This includes tracking and modifying window size,
 * position, titles, styling, and display modes (like fullscreen or vertical
 * synchronization).
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */
public class WindowProperties {
    private final Window window;

    private final int[] x = new int[1], y = new int[1],
            width = new int[1], height = new int[1];

    private String title;
    private Color3 baseColor = new Color3(0x000000);
    private Icon icon;

    private boolean decorated;
    private boolean resizable;
    private boolean fullscreen;
    private boolean vsync;

    // The WindowProperties main constructor.
    WindowProperties(Window window, String title, int width, int height) {
        this.window = window;

        this.title = title;
        this.width[0] = width;
        this.height[0] = height;

        glfwGetWindowPos(window.asLong(), this.x, this.y);
    }

    /** @return the current X-coordinate of the window's upper-left corner */
    public int getX() { return x[0]; }

    /** @return the current Y-coordinate of the window's upper-left corner */
    public int getY() { return y[0]; }

    /** @return the current width of the window in screen coordinates */
    public int getWidth() { return width[0]; }

    /** @return the current height of the window in screen coordinates */
    public int getHeight() { return height[0]; }

    /** @return the current window title */
    public String getTitle() { return title; }

    /** @return the background clear color used by this window */
    public Color3 getBaseColor() { return baseColor; }

    /** @return the current icon used by the window, or {@code null} if unset */
    public Icon getIcon() { return icon; }

    /** @return {@code true} if the window has system decorations enabled */
    public boolean isDecorated() { return decorated; }

    /** @return {@code true} if the window can be resized by the user */
    public boolean isResizable() { return resizable; }

    /** @return {@code true} if the window is running in fullscreen mode */
    public boolean isFullscreen() { return fullscreen; }

    /** @return {@code true} if V-Sync is currently enabled */
    public boolean isVsync() { return vsync; }

    /**
     * Changes the screen position of the window.
     *
     * @param x the new X-coordinate
     * @param y the new Y-coordinate
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setPosition(int x, int y) {
        this.x[0] = x; this.y[0] = y;
        glfwSetWindowPos(window.asLong(), x, y);
        return this;
    }

    /**
     * Resizes the window dimensions.
     *
     * @param width  the new width in screen coordinates
     * @param height the new height in screen coordinates
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setSize(int width, int height) {
        this.width[0] = width; this.height[0] = height;
        glfwSetWindowPos(window.asLong(), width, height);
        return this;
    }

    /**
     * Updates the title text displayed on the window frame.
     *
     * @param title the new window title
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(window.asLong(), title);
        return this;
    }

    /**
     * Sets the custom canvas background color.
     *
     * @param color the new base clear color
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setBaseColor(Color3 color) {
        this.baseColor = color;
        return this;
    }

    /**
     * Loads and updates the window's taskbar and title-bar icon.
     *
     * @param icon the new custom icon asset to apply
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setIcon(Icon icon) {
        this.icon = icon;

        this.icon.load();
        this.icon.applyToWindow(window.asLong());

        return this;
    }

    /**
     * Toggles the window decorations (borders, close buttons, widgets).
     *
     * @param decorated {@code true} to enable borders/controls; {@code false} for borderless
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setDecorated(boolean decorated) {
        this.decorated = decorated;
        glfwSetWindowAttrib(window.asLong(), GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
        return this;
    }

    /**
     * Toggles whether the window frame is manually resizable by user interaction.
     *
     * @param resizable {@code true} to allow resizing; {@code false} to lock dimensions
     * @return this {@code WindowProperties} instance for method chaining
     */
    public WindowProperties setResizable(boolean resizable) {
        this.resizable = resizable;
        glfwSetWindowAttrib(window.asLong(), GLFW_DECORATED, resizable ? GLFW_TRUE : GLFW_FALSE);
        return this;
    }

    /**
     * Toggles between fullscreen and windowed display mode.
     *
     * @param fullscreen {@code true} to switch to full display; {@code false} to revert to windowed
     */
    public WindowProperties setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;

        Monitor monitor = window.getMonitor();
        glfwSetWindowMonitor(
                window.asLong(),
                fullscreen ? monitor.asLong() : 0L,

                fullscreen ? 0 : x[0],
                fullscreen ? 0 : y[0],

                fullscreen ? monitor.getWidth() : width[0],
                fullscreen ? monitor.getHeight() : height[0],
                fullscreen ? monitor.getRefreshRate() : GLFW_DONT_CARE
        );
        return this;
    }

    /**
     * Sets the vertical synchronization state, locking the frame rate to the monitor refresh interval.
     *
     * @param vsync {@code true} to lock frame buffer swapping to monitor cycles; {@code false} to uncap
     */
    public WindowProperties setVsync(boolean vsync) {
        this.vsync = vsync;
        glfwSwapInterval(vsync ? 1 : 0);
        return this;
    }
}