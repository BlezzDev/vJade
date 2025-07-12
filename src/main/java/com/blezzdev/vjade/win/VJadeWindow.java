package com.blezzdev.vjade.win;

import com.blezzdev.vjade.util.color.*;
import com.blezzdev.vjade.util.Scene;
import com.blezzdev.vjade.win.low.*;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class VJadeWindow {
    private long window;
    private String currentScene;
    private VJadeColorHSV backgroundColor = new VJadeColorHSV(0.2f, 0.3f, 0.3f);
    private int fps = 0;
    private boolean stopped = false;

    public VJadeWindow() { this(800, 600, "vJade"); }
    public VJadeWindow(int width, int height, String title) { initWindow(width, height, title); }

    private void initWindow(int width, int height, String title) {
        window = new VJCreateWin().create(width, height, title);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glViewport(0, 0, width, height);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, height, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    private void loop() {
        int frames = 0;
        long timer = System.currentTimeMillis();

        String last = currentScene;
        Scene.getSceneByName(currentScene).start();

        while (!glfwWindowShouldClose(window)) {

            // Set background color.

            glClearColor(backgroundColor.getR(), backgroundColor.getG(), backgroundColor.getB(), backgroundColor.getA());
            glClear(GL_COLOR_BUFFER_BIT);

            // Count FPS.

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                fps = frames;
                frames = 0;
                timer += 1000;
            }

            // Render Properties.

            if (!Objects.equals(last, currentScene)) {
                Scene.getSceneByName(last).finish();
                Scene.getSceneByName(currentScene).start();
                last = currentScene;
            }

            Scene.getSceneByName(currentScene).update();
            if (stopped) { break; }

            // Extras settings.

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
        Scene.getSceneByName(currentScene).finish();
    }

    public void enableVsync() { glfwSwapInterval(1); }

    public void run() { loop(); }

    public void stop() { stopped = true; }

    public void show() { glfwShowWindow(window); }
    public void hide() { glfwHideWindow(window); }

    // VJadeWindow Setters.

    public void setBackgroundColor(VJadeColorHSV color) { backgroundColor = color; }
    public void setBackgroundColor(VJadeColorRGB color) { backgroundColor = color.toHSVColor(); }
    public void setSize(int width, int height) { glfwSetWindowSize(window, width, height); }
    public void setCurrentScene(String name) { currentScene = name; }

    // VJadeWindow Getters.

    public int getFPS() { return fps; }
    public VJadeColorHSV getBackgroundColorHSV() { return backgroundColor; }
    public VJadeColorRGB getBackgroundColorRGB() { return backgroundColor.toRGBColor(); }
}