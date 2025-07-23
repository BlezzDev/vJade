package com.blezzdev.vjade.win;

import com.blezzdev.vjade.scenes.Scene2D;
import com.blezzdev.vjade.scenes.VJadeSceneRegistries;
import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.ColorHSV;
import com.blezzdev.vjade.util.color.ColorRGB;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

/**
   * It is not recommended to use
   * this class since it works
   * for internal functionalities of the library.
 */

/*
*  VJadeWindowLogic is a private class in raw code
*  to manage the basic configurations of a window.
*/

public class VJadeWindowLogic {
    private long window;
    private ColorHSV backgroundColor = new ColorHSV(0.2f, 0.3f, 0.3f);
    private String currentScene;
    private String previousScene;
    private Scene2D scene;

    private int fps = 0;
    private boolean stopped = false;

    public VJadeWindowLogic(int width, int height, String title) {
        window = VJadeWindowConstruct.create(new Vector2(width, height), title);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        VJadeWindowConstruct.configurate(new Vector2(width, height));
    }

    protected void loop() {
        int frames = 0;
        long timer = System.currentTimeMillis();

        previousScene = currentScene;
        scene = VJadeSceneRegistries.getScene(currentScene);
        
        VJadeWindowUtils.beforeLoop(currentScene, scene);
        while (!glfwWindowShouldClose(window)) {
            VJadeWindowUtils.setBackgroundColor(backgroundColor);

            // Calculate real-time FPS value.

            frames++;
            if (System.currentTimeMillis() - timer > 1000) { fps = frames; frames = 0; timer += 1000; }
            
            runTasks();
            if (stopped) { break; }
        }
        scene.finish();
    }

    private void runTasks() {
        previousScene = VJadeWindowUtils.updateScene(currentScene, previousScene);
        scene = VJadeSceneRegistries.getScene(currentScene);
        if (scene != null) {
            scene.render();
            scene.update();
        }

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void enableVsync() { glfwSwapInterval(1); }
    public void run() { glfwShowWindow(window); loop(); }
    public void stop() { stopped = true; glfwHideWindow(window); }

    public void setBackgroundColor(ColorHSV color) { backgroundColor = color; }
    public void setBackgroundColor(ColorRGB color) { backgroundColor = color.toHSVColor(); }
    public void setScene(String name) { currentScene = name; }

    public int getFPS() { return fps; }
    public long getWindow() { return window; }
    public ColorHSV getBackgroundColorHSV() { return backgroundColor; }
    public ColorRGB getBackgroundColorRGB() { return backgroundColor.toRGBColor(); }
}