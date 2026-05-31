package com.blezzdev.vjade.core.window;

import org.lwjgl.glfw.GLFW;

/**
 * Represents the main application window. It encapsulates the underlying GLFW
 * window handle along with its associated OpenAL audio device and context
 * handles, serving as the primary access point for window state, runtime
 * {@link WindowProperties}, and {@link Monitor} assignments.
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */

public class Window {
    private final long window;
    private final long device;
    private final long context;

    private final WindowProperties properties;
    private final Monitor monitor;

    // WindowBuilder main constructor.
    Window
            (long window, long device, long context,
             String title, int width, int height)
    {
        this.window = window;
        this.device = device;
        this.context = context;
        this.properties = new WindowProperties(this, title, width, height);
        this.monitor = new Monitor(GLFW.glfwGetPrimaryMonitor());
    }

    /**
     * Exposes the underlying native window pointer.
     *
     * @return this window instance as a raw pointer handle ({@code long})
     */
    public long asLong() { return window; }

    /**
     * Exposes the underlying OpenAL audio hardware device pointer.
     *
     * @return the audio device handle from the active OpenAL state
     */
    public long getDevice() { return device; }

    /**
     * Exposes the underlying OpenAL execution context pointer.
     *
     * @return the active OpenAL context handle
     */
    public long getContext() { return context; }

    /**
     * Provides access to mutable configurations, display modes, and dimensions.
     *
     * @return the {@link WindowProperties} manager linked to this window instance
     */
    public WindowProperties getProperties() { return properties; }

    /**
     * Provides access to physical display metrics and configurations.
     *
     * @return the {@link Monitor} context representing the hardware display target
     */
    public Monitor getMonitor() { return monitor; }
}