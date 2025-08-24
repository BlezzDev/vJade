package com.blezzdev.vjade.elements.basic.node;

public interface NodeStructure {
    default void start() {}
    default void render() {}
    default void update(double deltaTime) {}
    default void end() {}
}