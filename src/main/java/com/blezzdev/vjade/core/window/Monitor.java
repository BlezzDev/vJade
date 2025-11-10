package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.tools.data.geometry.Vec2;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

/**
 * The {@code Monitor} class is responsible for <b>retrieving and storing information</b>
 * about the display monitor where the application window is hosted.
 *
 * <p>The following parameters can be accessed:</p>
 *
 * <ul>
 *   <li><b>Resolution</b> obtains the screen resolution.</li>
 *   <li><b>Refresh rate</b> retrieves the monitor’s refresh rate (in Hertz).</li>
 *   <li><b>Red, Green,</b> and <b>Blue bits</b> report the number of bits used for each color channel.</li>
 * </ul>
 *
 * @author Alfaro
 * @version 1.0
 * @since vJade 1.0
 */


public class Monitor {
    private final GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

    public Vec2 getResolution() {
        return new Vec2(videoMode.width(), videoMode.height());
    }
    public int getRefreshRate() { return videoMode.refreshRate(); }
    public int getRedBits() {
        return videoMode.redBits();
    }
    public int getGreenBits() {
        return videoMode.greenBits();
    }
    public int getBlueBits() {
        return videoMode.blueBits();
    }
}
