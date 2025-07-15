package com.blezzdev.vjade.util;

import com.blezzdev.vjade.win.VJadeWindow;

import java.util.HashMap;
import java.util.Map;

public class VJadeScene {
    private static final Map<String, VJadeScene> REGISTERED_SCENES = new HashMap<>();

    private final String name;
    private final VJadeWindow window;

    public VJadeScene(VJadeWindow window, String name) {
        REGISTERED_SCENES.put(name, this);
        this.name = name;
        this.window = window;
    }

    public void start() {}
    public void render() {}
    public void update() {}
    public void finish() {}

    public static final VJadeScene getSceneByName(String name) { return REGISTERED_SCENES.get(name); }
    public final VJadeWindow getWindow() { return window; }
    public final String getName() { return name; }
}