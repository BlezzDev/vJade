package com.blezzdev.vjade.nodes.collision;

import com.blezzdev.vjade.nodes.Node;
import com.blezzdev.vjade.util.CollisionProvider;
import com.blezzdev.vjade.util.Vector2;

public class CollisionShape extends Node {
    public Vector2 size;
    private int id;

    private int layer = -1;
    private String group = "";

    protected boolean colliding = false;

    public CollisionShape(Vector2 position, Vector2 size) {
        super(position);

        this.size = size;
        id = CollisionProvider.register(this);
    }

    public boolean isColliding() { return colliding; }
    public Vector2 getSize() { return size; }
    public int getId() { return id; }

    public void setSize(Vector2 size) { this.size = size; }

    @Override
    public void destroy() {
        super.destroy();
        CollisionProvider.unregister(getId());
    }
}