package com.blezzdev.vjade.nodes.collision;

import com.blezzdev.vjade.nodes.Node2D;
import com.blezzdev.vjade.util.VJadeCollisionRegistries;
import com.blezzdev.vjade.util.Vector2;

public class VJadeCollisionShape extends Node2D {
    public Vector2 size;

    private final int id;
    private int layer = -1;
    private boolean colliding = false;

    public VJadeCollisionShape(Vector2 position, Vector2 size) {
        super(position);

        this.size = size;
        id = VJadeCollisionRegistries.register(this);
    }

    public boolean isColliding() { return colliding; }
    public Vector2 getSize() { return size; }
    public int getId() { return id; }

    public void setSize(Vector2 size) { this.size = size; }

    @Override
    public void destroy() {
        super.destroy();
        VJadeCollisionRegistries.unregister(getId());
    }

    public int getLayer() { return layer; }
    public boolean getCollidingState() { return colliding; }

    public void setLayer(int layer) { this.layer = layer; }
    public void setCollidingState(boolean value) { this.colliding = value; }
}