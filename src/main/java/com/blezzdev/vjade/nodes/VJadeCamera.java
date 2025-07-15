package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.VJadeVector2;
import com.blezzdev.vjade.win.VJadeWindow;

import static org.lwjgl.opengl.GL11.*;

public class VJadeCamera extends VJadeVisualNode {
    private VJadeVector2 size;
    private VJadeVisualNode pivot;

    public VJadeCamera(VJadeWindow window, VJadeVisualNode pivot) {
        this.pivot = pivot;
        this.size = window.getSize();
    }

    @Override
    public void render() {
        super.render();

        VJadeVector2 center = pivot.getPosition();
        VJadeVector2 halfSize = size.divide(2);

        float left   = center.x - halfSize.x;
        float right  = center.x + halfSize.x;
        float top    = center.y - halfSize.y;
        float bottom = center.y + halfSize.y;

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(left, right, bottom, top, -1, 1);
        glMatrixMode(GL_MODELVIEW);
    }

    public VJadeVisualNode getPivot() { return pivot; }
    public VJadeVector2 getSize() { return size; }

    public void setPivot(VJadeVisualNode pivot) { this.pivot = pivot; }
    public void setSize(VJadeVector2 size) { this.size = size; }
}
