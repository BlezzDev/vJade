package com.blezzdev.vjade.win;

import com.blezzdev.vjade.util.VJadeVector2;

public class VJadeWindow extends VJadeWindowStructure {
    private VJadeVector2 size;

    public VJadeWindow() { this(800, 600, "vJade"); }
    public VJadeWindow(int width, int height, String title) {
        super(width, height, title);

        size = new VJadeVector2(width, height);
    }

    // VJadeWindow Getters.

    public VJadeVector2 getSize() { return size; }

    // VjadeWindow Setters.

    public void setSize(VJadeVector2 size) { this.size = size; }
}