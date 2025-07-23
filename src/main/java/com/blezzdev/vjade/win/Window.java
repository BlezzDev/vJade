package com.blezzdev.vjade.win;

import com.blezzdev.vjade.util.Vector2;

import static org.lwjgl.glfw.GLFW.*;

/*
*  Window is a public class for creating and managing a window.
*

*  To instance a window whit VJade just use this line code:
*  -> VJadeWindow window = new VJadeWindow(1124, 680, "vJade");

*  This class requires a scene to work.
*
*  First, you must instantiate the main scene and
*  the other scenes that are required for the project.

*  To instance a scene whit VJade, just use this line code:
*  -> new InstancedScene(win, "instanced_scene");

*  For set the main scene, use this line code:
*  -> win.setScene("instanced_scene");

*  For run the window, use this line code:
*  -> win.run();

*  Full example:

        VJadeWindow window = new VJadeWindow(1124, 680, "vJade");
        new MenuScene(window, "menu");

        window.setScene("menu");

        window.run();

*/

public class Window extends VJadeWindowLogic {
    private Vector2 size;
    private String title;

    public Window() { this(800, 600, "vJade"); }
    public Window(Vector2 size, String title) { this((int) (size.x), (int) (size.y), title); }
    public Window(int width, int height, String title) {
        super(width, height, title);

        this.title = title;
        this.size = new Vector2(width, height);
    }

    private void updateWindow() {
        glfwSetWindowSize(getWindow(), (int) (size.x), (int) (size.y));
        glfwSetWindowTitle(getWindow(), title);
    }

    public Vector2 getSize() { return size; }
    public String getTitle() { return title; }

    public void setSize(Vector2 size) { this.size = size; updateWindow(); }
    public void setSize(int width, int height) { setSize(new Vector2(width, height)); }
    public void setTitle(String title) { this.title = title; updateWindow(); }
}