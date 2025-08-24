package com.blezzdev.vjade.elements.basic;

import com.blezzdev.vjade.core.engine.Engine;
import com.blezzdev.vjade.core.engine.input.Input;
import com.blezzdev.vjade.core.engine.logger.Logger;
import com.blezzdev.vjade.core.window.WindowRender;
import com.blezzdev.vjade.core.window.tree.TreeManager;
import com.blezzdev.vjade.elements.basic.node.Node;

public class Scene extends Node {
    private Engine engine;

    public Scene(String name) {
        setName(name);
    }

    public Engine getEngine() { return engine; }
    public Logger getLogger() { return engine.getLogger(); }
    public TreeManager getTree() { return engine.getTree(); }
    public WindowRender getRenderer() { return engine.getRenderer(); }
    public Input getInput() { return engine.getInput(); }

    public void setEngine(Engine engine) { this.engine = engine; }
}
