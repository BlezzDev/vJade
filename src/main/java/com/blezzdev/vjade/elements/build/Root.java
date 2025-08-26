package com.blezzdev.vjade.elements.build;

import com.blezzdev.vjade.core.window.TreeManager;

public class Root extends Node {
    private String currentScene;

    public Root(TreeManager primary) {
        super(primary);

        primary.setPrimaryNode(this);
    }

    private void verifyNullSceneError(String main) {
        for (int i = 0; i < this.getChildrens().size(); i++) {
            Node child = getChildren(i);
            if (child.getName().equals(main)) { return; }
        }
        throw new NullSceneException(main);
    }

    public void init(String mainScene) {
        verifyNullSceneError(mainScene);
        this.currentScene = mainScene;
    }

    @Override
    public void setName(String name) {
        throw new RootInteractionError("The root cannot be modified.");
    }

    @Override
    public void setParent(Node parent) {
        throw new RootInteractionError("The root cannot be modified.");
    }

    // The start, render, update and die methods work the same,
    // it gets all its corresponding scenes and if the scene
    // name matches the given scene then it will execute
    // the corresponding method.

    @Override
    public void start() {
        super.start();
        for (int i = 0; i < getChildrens().size(); i++) {
            Node child = getChildren(i);
            if (child.getName().equals(currentScene)) { child.start(); }
        }
    }

    @Override
    public void render() {
        super.render();
        for (int i = 0; i < getChildrens().size(); i++) {
            Node child = getChildren(i);
            if (child.getName().equals(currentScene)) { child.render(); }
        }
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        for (int i = 0; i < getChildrens().size(); i++) {
            Node child = getChildren(i);
            if (child.getName().equals(currentScene)) { child.update(deltaTime); }
        }
    }
    @Override
    public void end() {
        super.end();
        for (int i = 0; i < getChildrens().size(); i++) {
            Node child = getChildren(i);
            if (child.getName().equals(currentScene)) { child.end(); }
        }
    }

    public String getSceneName() { return currentScene; }
    public void changeScene(String name) { this.currentScene = name; }
}
