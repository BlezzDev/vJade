package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.UserInterfaceManager;
import com.blezzdev.vjade.core.manager.collider.CollisionManager;
import com.blezzdev.vjade.core.window.Engine;
import com.blezzdev.vjade.core.debug.Logger;
import com.blezzdev.vjade.core.manager.input.InputManager;
import com.blezzdev.vjade.core.manager.timer.TimerManager;
import com.blezzdev.vjade.core.window.Monitor;
import com.blezzdev.vjade.tools.VJade;

import java.util.ArrayList;
import java.util.List;

public class VJObject implements LogicProcesses {
    private final List<VJObject> objectList = new ArrayList<>();

    public <T extends VJObject> T instance(T object) {
        objectList.add(object);
        return object;
    }

    @Override
    public void init() {
        LogicProcesses.super.init();

        for (VJObject object : objectList) {
            object.init();
        }
    }

    @Override
    public void start() {
        LogicProcesses.super.start();

        for (VJObject object : objectList) {
            object.start();
        }
    }

    @Override
    public void update(double deltaTime) {
        LogicProcesses.super.update(deltaTime);

        for (VJObject object : objectList) {
            object.update(deltaTime);
        }
    }

    @Override
    public void end_scene() {
        LogicProcesses.super.end_scene();

        for (VJObject object : objectList) {
            object.end_scene();
        }
    }

    @Override
    public void finish() {
        LogicProcesses.super.finish();

        for (VJObject object : objectList) {
            object.finish();
        }
    }

    @Override
    public void end_program() {
        LogicProcesses.super.end_program();

        for (VJObject object : objectList) {
            object.end_program();
        }
    }

    public void changeScene(String identifier) { getContext().changeScreen(identifier); }

    public Engine getContext() { return VJade.getContext(); }
    public Logger getLogger() { return getContext().getLogger(); }
    public Monitor getMonitor() { return getContext().getMonitor(); }

    public UserInterfaceManager getUserInterface() { return getContext().getUserInterface(); }
    public CollisionManager getCollision() { return getContext().getManagers().getCollision(); }
    public ScreenManager getScreen() { return getContext().getManagers().getScreen(); }
    public TimerManager getTimer() { return getContext().getManagers().getTimer(); }
    public InputManager getInput() { return getContext().getManagers().getInput(); }
}