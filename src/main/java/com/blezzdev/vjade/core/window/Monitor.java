package com.blezzdev.vjade.core.window;

import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

/**
 * Retrieving and storing information about the display monitor where the
 * application {@link Window} is hosted. It encapsulates the monitor properties
 * how dimensions, refresh rate and color channel bits.
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */

public class Monitor {
    private final GLFWVidMode videoMode;
    private final long monitor;

    Monitor(long monitor) {
        this.videoMode = glfwGetVideoMode(monitor);
        this.monitor = monitor;
    }

    /**
     * Exposes the monitor width where the {@link Window} are hosted.
     *
     * @return the width of the monitor.
     */
    public int getWidth() { return videoMode.width(); }

    /**
     * Exposes the monitor height where the {@link Window} are hosted.
     *
     * @return the height of the monitor.
     */
    public int getHeight() { return videoMode.height(); }

    /**
     * Exposes the monitor refresh rate where the {@link Window} are hosted.
     *
     * @return the refresh rate of the monitor.
     */
    public int getRefreshRate() { return videoMode.refreshRate(); }

    /**
     * Provides the bits of red color channel of the monitor.
     *
     * @return the red bits of the monitor.
     */
    public int getRedBits() { return videoMode.redBits(); }

    /**
     * Provides the bits of green color channel of the monitor.
     *
     * @return the green bits of the monitor.
     */
    public int getGreenBits() { return videoMode.greenBits(); }

    /**
     * Provides the bits of blue color channel of the monitor.
     *
     * @return the blue bits of the monitor.
     */
    public int getBlueBits() { return videoMode.blueBits(); }

    /**
     * Exposes the underlying native monitor pointer.
     *
     * @return this monitor instance as a raw pointer handle ({@code long})
     */
    public long asLong() { return monitor; }
}
