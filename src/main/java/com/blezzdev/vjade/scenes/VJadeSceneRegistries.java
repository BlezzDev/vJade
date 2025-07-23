package com.blezzdev.vjade.scenes;

import java.util.HashMap;
import java.util.Map;

/**
 * It is not recommended to use
 * this class since it works
 * for internal functionalities of the library.
 */

/*
 * VJadeSceneRegistries is a private class for register collisions.
 */

public class VJadeSceneRegistries {
    private static final Map<String, Scene2D> REGISTERED_SCENES = new HashMap<>();

    public static void register(String name, Scene2D scene) {
        REGISTERED_SCENES.put(name, scene);
    }

    public static Scene2D getScene(String name) {
        return REGISTERED_SCENES.get(name);
    }
}
