package com.blezzdev.vjade.objects.processes;

public interface LogicProcesses {
    default void start() {}
    default void update(double deltaTime) {}
    default void finish() {}
}
