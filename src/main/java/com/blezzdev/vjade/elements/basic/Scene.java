package com.blezzdev.vjade.elements.basic;

import com.blezzdev.vjade.core.engine.Engine;
import com.blezzdev.vjade.elements.basic.node.Node;

public class Scene extends Node {
    private Engine engine;

    public Scene(String name) {
        setName(name);
    }

    public Engine getEngine() { return engine; }

    public void setEngine(Engine engine) { this.engine = engine; }
}
