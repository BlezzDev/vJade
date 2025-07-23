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
    private static final Map<String, Scene> REGISTERED_SCENES = new HashMap<>();

    public static void register(String name, Scene scene) {
        REGISTERED_SCENES.put(name, scene);
    }

    public static Scene getScene(String name) {
        return REGISTERED_SCENES.get(name);
    }
}
