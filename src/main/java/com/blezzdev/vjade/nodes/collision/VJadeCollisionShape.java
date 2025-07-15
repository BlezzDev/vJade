package com.blezzdev.vjade.nodes.collision;

import com.blezzdev.vjade.nodes.VJadeVisualNode;
import com.blezzdev.vjade.util.VJadeCollisionProvider;
import com.blezzdev.vjade.util.VJadeVector2;

public class VJadeCollisionShape extends VJadeVisualNode {
    public VJadeVector2 size;
    private int id;

    private int layer = -1;
    private String group = "";

    protected boolean colliding = false;

    public VJadeCollisionShape(VJadeVector2 position, VJadeVector2 size) {
        super(position);

        this.size = size;
        id = VJadeCollisionProvider.register(this);
    }

    public boolean isColliding() { return colliding; }
    public VJadeVector2 getSize() { return size; }
    public int getId() { return id; }

    public void setSize(VJadeVector2 size) { this.size = size; }

    @Override
    public void destroy() {
        super.destroy();
        VJadeCollisionProvider.unregister(getId());
    }
}