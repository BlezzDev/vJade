package com.blezzdev.vjade.objects.build;

interface LogicProcesses {
    default void init() {}
    default void start() {}
    default void update(double deltaTime) {}
    default void finish() {}
    default void end_scene() {}
    default void end_program() {}
}
