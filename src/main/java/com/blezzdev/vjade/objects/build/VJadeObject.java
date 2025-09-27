package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.objects.processes.LogicProcesses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VJadeObject<T extends VJadeObject<T>> implements LogicProcesses {
    private final List<VJadeObject<?>> objectList = new ArrayList<>();
    private Window window;

    public <T extends VJadeObject<T>> T add(T object) {
        objectList.add(object);
        return object;
    }

    @Override
    public void start() {
        LogicProcesses.super.start();

        for (VJadeObject<?> object : objectList) {
            object.start();
        }
    }

    @Override
    public void update(double deltaTime) {
        LogicProcesses.super.update(deltaTime);

        for (VJadeObject<?> object : objectList) {
            object.setWindow(window);

            object.update(deltaTime);
        }
    }

    @Override
    public void finish() {
        LogicProcesses.super.finish();

        for (VJadeObject<?> object : objectList) {
            object.finish();
        }
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Window getWindow() { return window; }
}
