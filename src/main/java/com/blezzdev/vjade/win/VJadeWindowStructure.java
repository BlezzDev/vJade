package com.blezzdev.vjade.win;

import com.blezzdev.vjade.util.VJadeScene;
import com.blezzdev.vjade.util.VJadeVector2;
import com.blezzdev.vjade.util.color.VJadeColorHSV;
import com.blezzdev.vjade.util.color.VJadeColorRGB;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class VJadeWindowStructure {
    private long window;
    private VJadeColorHSV backgroundColor = new VJadeColorHSV(0.2f, 0.3f, 0.3f);
    private String currentScene;

    private int fps = 0;
    private boolean stopped = false;

    public VJadeWindowStructure(int width, int height, String title) {
        window = VJadeWindowManage.create(new VJadeVector2(width, height), title);

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        VJadeWindowManage.configurate(new VJadeVector2(width, height));
    }

    private void backgroundFunction() {
        glClearColor(backgroundColor.getR(), backgroundColor.getG(), backgroundColor.getB(), backgroundColor.getA());
        glClear(GL_COLOR_BUFFER_BIT);
    }

    private String sceneChanged(String last) {
        if (!Objects.equals(last, currentScene)) {
            VJadeScene oldScene = VJadeScene.getSceneByName(last);
            VJadeScene newScene = VJadeScene.getSceneByName(currentScene);

            if (oldScene != null) oldScene.finish();
            if (newScene != null) newScene.start();

            last = currentScene;
        }
        return last;
    }

    protected void loop() {
        int frames = 0;
        long timer = System.currentTimeMillis();

        String last = currentScene;
        VJadeScene scene = VJadeScene.getSceneByName(currentScene);
        if (currentScene != null) {
            scene.start();
        } else {
            System.err.println("VJadeWindow requires a initial scene to work.");
            return;
        }

        while (!glfwWindowShouldClose(window)) {
            backgroundFunction();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                fps = frames;
                frames = 0;
                timer += 1000;
            }

            // Render Properties.
            last = sceneChanged(last);

            scene.render();
            scene.update();
            if (stopped) { break; }

            // Extras settings.

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
        scene.finish();
    }

    public void enableVsync() { glfwSwapInterval(1); }
    public void run() { glfwShowWindow(window); loop(); }
    public void stop() { stopped = true; glfwHideWindow(window); }

    // VJadeWindowStructure Setters.

    public void setBackgroundColor(VJadeColorHSV color) { backgroundColor = color; }
    public void setBackgroundColor(VJadeColorRGB color) { backgroundColor = color.toHSVColor(); }
    public void setScene(String name) { currentScene = name; }

    // VJadeWindowStructure Getters.

    public int getFPS() { return fps; }
    public VJadeColorHSV getBackgroundColorHSV() { return backgroundColor; }
    public VJadeColorRGB getBackgroundColorRGB() { return backgroundColor.toRGBColor(); }
}
