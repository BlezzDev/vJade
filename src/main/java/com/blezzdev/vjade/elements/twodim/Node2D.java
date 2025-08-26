package com.blezzdev.vjade.elements.twodim;

import com.blezzdev.vjade.elements.build.Node;
import com.blezzdev.vjade.tools.Vector2;

public class Node2D extends Node {
    private Vector2 position = Vector2.ZERO;
    private int rotation = 0;
    private int zIndex = 0;

    public Node2D() {}
    public Node2D(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() { return position; }
    public int getRotation() { return rotation; }

    public void setPosition(Vector2 position) { this.position = position; }
    public void setRotation(int rotation) { this.rotation = rotation; }

    public void moveX(float value) { this.position.setX(this.position.getX() + value); }
    public void moveY(float value) { this.position.setY(this.position.getY() + value); }
}
