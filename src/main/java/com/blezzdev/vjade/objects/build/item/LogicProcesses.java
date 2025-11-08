package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.core.manager.canvas.CanvasManager;

interface LogicProcesses {
    default void init() {}
    default void start() {}
    default void update(float deltaTime) {}
    default void render(CanvasManager canvas) {}
    default void finish() {}
    default void end_scene() {}
    default void end_program() {}
}