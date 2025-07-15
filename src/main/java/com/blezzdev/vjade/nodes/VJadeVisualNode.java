package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.VJadeVector2;

public class VJadeVisualNode extends VJadeNode {
    protected VJadeVector2 position = new VJadeVector2(0, 0);

    public VJadeVisualNode() { this(0, 0); }
    public VJadeVisualNode(VJadeVector2 pos) { this((int) pos.x, (int) pos.y); }
    public VJadeVisualNode(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public void moveX(int value) { position.x = position.x + value; }
    public void moveY(int value) { position.y = position.y + value; }

    public VJadeVector2 getPosition() { return position; }

    public void setPosition(VJadeVector2 position) { this.position = position; }
}
