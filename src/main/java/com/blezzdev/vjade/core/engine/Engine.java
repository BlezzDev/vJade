package com.blezzdev.vjade.core.engine;

import com.blezzdev.vjade.core.input.Input;
import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.core.window.WindowRender;
import com.blezzdev.vjade.core.window.TreeManager;
import com.blezzdev.vjade.elements.build.Root;
import com.blezzdev.vjade.elements.build.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Engine extends Window {
    private TreeManager tree = new TreeManager();
    private List<Scene> registeredScenes = new ArrayList<>();
    private Logger logger = new Logger();
    private WindowRender windowRender = new WindowRender();

    public void runEngine(String mainScene) {
        glfwShowWindow(glWindow);
        Root root = new Root(tree);

        for (int i = 0; i < registeredScenes.size(); i++) {
            registeredScenes.get(i).setParent(root);
        }

        root.init(mainScene);

        try {
            windowRender.init(this, getBGColor(), root); // Create a render class.


            // Free the window callbacks and destroy the window.
            glfwFreeCallbacks(glWindow);
            glfwDestroyWindow(glWindow);
        } finally {

            // Terminate GLFW and free the error callback.
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    public Engine addScene(Scene scene) { addScene(scene, scene.getClass().getSimpleName()); return this; }
    public Engine addScene(Scene scene, String name) {
        registeredScenes.add(scene);
        scene.setName(name);
        scene.setEngine(this);
        return this;
    }

    public void changeScene(String name) { getTree().getPrimaryNode().changeScene(name); }

    public Logger getLogger() { return logger; }
    public TreeManager getTree() { return tree; }
    public WindowRender getResources() { return windowRender; }
    public Input getInput() { return input; }
}