package com.blezzdev.vjade.core.engine;

import com.blezzdev.vjade.core.engine.logger.Logger;
import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.core.window.WindowRender;
import com.blezzdev.vjade.core.window.tree.TreeManager;
import com.blezzdev.vjade.elements.basic.Root;
import com.blezzdev.vjade.elements.basic.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Engine extends Window {
    private TreeManager tree = new TreeManager();
    private List<Scene> registeredScenes = new ArrayList<>();
    private Logger logger = new Logger();

    public void runEngine(String mainScene) {
        glfwShowWindow(glWindow);
        Root root = new Root(tree);

        for (int i = 0; i < registeredScenes.size(); i++) {
            registeredScenes.get(i).setParent(root);
        }

        root.init(mainScene);

        try {
            WindowRender render = new WindowRender(glWindow, getBGColor(), root); // Create a render class.



            // Free the window callbacks and destroy the window.
            glfwFreeCallbacks(glWindow);
            glfwDestroyWindow(glWindow);
        } finally {

            // Terminate GLFW and free the error callback.
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    public Engine addScene(Scene scene) {
        registeredScenes.add(scene);
        scene.setEngine(this);
        return this;
    }

    public Logger getLogger() { return logger; }
    public TreeManager getTree() { return tree; }
}
