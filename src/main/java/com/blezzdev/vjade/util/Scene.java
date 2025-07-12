package com.blezzdev.vjade.util;

import com.blezzdev.vjade.win.VJadeWindow;

import java.util.HashMap;
import java.util.Map;

public class Scene {
    private static final Map<String, Scene> REGISTERED_SCENES = new HashMap<>();

    private final String name;
    private final VJadeWindow window;

    public Scene(VJadeWindow window, String name) {
        REGISTERED_SCENES.put(name, this);
        this.name = name;
        this.window = window;
    }

    public void start() {}
    public void update() {}
    public void finish() {}

    public static final Scene getSceneByName(String name) { return REGISTERED_SCENES.get(name); }
    public final VJadeWindow getWindow() { return window; }
    public final String getName() { return name; }
}