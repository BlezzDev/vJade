package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.Vector2;

public class VNode extends Node {
    protected Vector2 position = new Vector2(0, 0);

    public VNode() { this(0, 0); }
    public VNode(Vector2 pos) { this((int) pos.x, (int) pos.y); }
    public VNode(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void moveX(int value) { position.x = position.x + value; }
    public void moveY(int value) { position.y = position.y + value; }

    public Vector2 getPosition() { return position; }

    public void setPosition(Vector2 position) { this.position = position; }
}
