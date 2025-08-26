package com.blezzdev.vjade.elements.build;

import com.blezzdev.vjade.core.engine.Engine;
import com.blezzdev.vjade.core.input.Input;
import com.blezzdev.vjade.core.engine.Logger;
import com.blezzdev.vjade.core.window.WindowRender;
import com.blezzdev.vjade.core.window.TreeManager;

public class Scene extends Node {
    private Engine engine;

    public Engine getEngine() { return engine; }
    public Logger getLogger() { return engine.getLogger(); }
    public TreeManager getTree() { return engine.getTree(); }
    public WindowRender getResources() { return engine.getResources(); }
    public Input getInput() { return engine.getInput(); }

    public void setEngine(Engine engine) { this.engine = engine; }
}
