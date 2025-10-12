package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.core.window.Engine;
import com.blezzdev.vjade.core.debug.Logger;
import com.blezzdev.vjade.core.input.Input;
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
    public void finish() {
        LogicProcesses.super.finish();

        for (VJObject object : objectList) {
            object.finish();
        }
    }

    public void changeScene(String identifier) { getContext().changeScreen(identifier); }

    public Engine getContext() { return VJade.getContext(); }
    public Logger getLogger() { return getContext().getLogger(); }
    public Input getInput() { return getContext().getInput(); }
    public Monitor getMonitor() { return getContext().getMonitor(); }

    public TimerManager getTimer() { return getContext().getTimerManager(); }
}