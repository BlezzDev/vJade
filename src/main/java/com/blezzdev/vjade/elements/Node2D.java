package com.blezzdev.vjade.elements;

import com.blezzdev.vjade.elements.basic.node.Node;
import com.blezzdev.vjade.tools.Vector2;

public class Node2D extends Node {
    private Vector2 position = new Vector2();

    public Vector2 getPosition() { return position; }

    public void setPosition(Vector2 position) { this.position = position; }

    public void moveX(float value) { this.position.setX(value); }
    public void moveY(float value) { this.position.setY(value); }
}
