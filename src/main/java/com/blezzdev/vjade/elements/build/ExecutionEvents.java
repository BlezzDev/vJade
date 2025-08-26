package com.blezzdev.vjade.elements.build;

public interface ExecutionEvents {
    default void start() {}
    default void render() {}
    default void update(double deltaTime) {}
    default void end() {}
}