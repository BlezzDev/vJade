package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.objects.processes.LogicProcesses;

import java.util.ArrayList;
import java.util.List;

public class VJObject implements LogicProcesses {
    private final List<VJObject> objectList = new ArrayList<>();

    public <T extends VJObject> T create(T object) {
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
}
