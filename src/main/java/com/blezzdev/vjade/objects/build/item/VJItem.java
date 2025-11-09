package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.UserInterfaceManager;
import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.core.manager.collider.CollisionManager;
import com.blezzdev.vjade.core.window.Engine;
import com.blezzdev.vjade.core.debug.Logger;
import com.blezzdev.vjade.core.manager.input.InputManager;
import com.blezzdev.vjade.core.manager.timer.TimerManager;
import com.blezzdev.vjade.core.window.Monitor;
import com.blezzdev.vjade.tools.VJade;

import java.util.ArrayList;
import java.util.List;

public class VJItem implements LogicProcesses {
    private final List<VJItem> objectList = new ArrayList<>();

    // Insert a new object as a child object.

    public <T extends VJItem> T instance(T object) {
        objectList.add(object);
        return object;
    }

    // Declarative functions of the program life cycle.

    @Override
    public void init() {
        LogicProcesses.super.init();
        objectList.forEach(obj -> obj.init());
    }
    @Override
    public void start() {
        LogicProcesses.super.start();
        objectList.forEach(obj -> obj.start());
    }
    @Override
    public void update(float deltaTime) {
        LogicProcesses.super.update(deltaTime);
        objectList.forEach(obj -> obj.update(deltaTime));
    }
    @Override
    public void render(CanvasManager canvas) {
        LogicProcesses.super.render(canvas);
        objectList.forEach(obj -> obj.render(canvas));
    }
    @Override
    public void end_scene() {
        LogicProcesses.super.end_scene();
        objectList.forEach(obj -> obj.end_scene());
    }
    @Override
    public void finish() {
        LogicProcesses.super.finish();
        objectList.forEach(obj -> obj.finish());
    }
    @Override
    public void end_program() {
        LogicProcesses.super.end_program();
        objectList.forEach(obj -> obj.end_program());
    }

    public void changeScene(String identifier) { getContext().changeScreen(identifier); }

    public Engine getContext() { return VJade.getContext(); }
    public Logger getLogger() { return getContext().getLogger(); }
    public Monitor getMonitor() { return getContext().getMonitor(); }

    public UserInterfaceManager getUserInterface() { return getContext().getUserInterface(); }
    public CollisionManager getCollision() { return getContext().getCollision(); }
    public ScreenManager getScreen() { return getContext().getScreen(); }
    public CanvasManager getCanvas() { return getContext().getCanvas(); }
    public TimerManager getTimer() { return getContext().getTimer(); }
    public InputManager getInput() { return getContext().getInput(); }
}