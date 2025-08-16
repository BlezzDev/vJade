package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.tree.TreeManager;
import com.blezzdev.vjade.elements.basic.Node;
import com.blezzdev.vjade.tools.Vector2;
import com.blezzdev.vjade.tools.color.Color;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Window extends WindowBuilder {
    private Color bgColor = new Color(0, 0, 0); // Starts with a black background.
    private TreeManager tree = new TreeManager();

    public Window() { this("", 800, 600); }
    public Window(String name, int width, int height) {
        super(name, width, height);
    }

    public void run() {
        glfwShowWindow(glWindow);
        createTree();

        try {
            new WindowRender(glWindow, bgColor); // Create a render class.

            // Free the window callbacks and destroy the window.
            glfwFreeCallbacks(glWindow);
            glfwDestroyWindow(glWindow);
        } finally {

            // Terminate GLFW and free the error callback.
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    // Creates a root node, which functions as the seed of the tree.

    private void createTree() {
        Node initialTree = new Node(tree);
        initialTree.setName("window");
    }

    // Local method to transform an integer value into its boolean variant.

    private int boolToInt(boolean bool) {
        if (bool) { return 1; }
        else { return 0; }
    }

    public void setTitle(String title) { glfwSetWindowTitle(glWindow, title); }
    public void setSize(Vector2 size) { setSize(size.getIntX(), size.getIntY()); }
    public void setSize(int width, int height) { glfwSetWindowSize(glWindow, width, height); }
    public void setDecorations(boolean value) { glfwSetWindowAttrib(glWindow, GLFW_DECORATED, boolToInt(value)); }
    public void setResizable(boolean value) { glfwSetWindowAttrib(glWindow, GLFW_RESIZABLE, boolToInt(value)); }
    public void setBackgroundColor(Color color) {
        bgColor = color;
    }

    public TreeManager getTree() { return tree; }
}