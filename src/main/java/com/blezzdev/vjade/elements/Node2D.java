package com.blezzdev.vjade.elements;

import com.blezzdev.vjade.elements.basic.node.Node;
import com.blezzdev.vjade.tools.Vector2;

public class Node2D extends Node {
    private Vector2 position;

    public Node2D() { this(Vector2.ZERO); }
    public Node2D(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() { return position; }

    public void setPosition(Vector2 position) { this.position = position; }

    public void moveX(float value) { this.position.setX(this.position.getX() + value); }
    public void moveY(float value) { this.position.setY(this.position.getY() + value); }
}
