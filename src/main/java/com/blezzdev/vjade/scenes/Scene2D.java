package com.blezzdev.vjade.scenes;

import com.blezzdev.vjade.win.Window;

/*
* Scene is a public class for create and manage custom scenes.

* For create your own custom scene, you
* need create a new class extended with this class and
* get the start, render, update and finish methods.

* Full example:

        public class CustomScene extends Scene {
            @Override public void start() { super.start(); }
            @Override public void render() { super.render(); }
            @Override public void update() { super.update(); }
            @Override public void finish() { super.finish(); }
        }

*/

public class Scene2D {
    private final String name;
    private final Window window;
    public Scene2D(Window window, String name) {
        VJadeSceneRegistries.register(name, this);
        this.name = name;
        this.window = window;
    }

    public void start() {}
    public void render() {}
    public void update() {}
    public void finish() {}
    public final Window getWindow() { return window; }
    public final String getName() { return name; }
}