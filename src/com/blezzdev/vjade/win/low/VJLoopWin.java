package com.blezzdev.vjade.win.low;

import com.blezzdev.vjade.util.color.VJadeColorHSV;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;

public class VJLoopWin {
    public void looping(long window, VJadeColorHSV color) {
        while (!glfwWindowShouldClose(window)) {
            glClearColor(color.getR(), color.getG(), color.getB(), color.getA());
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}
