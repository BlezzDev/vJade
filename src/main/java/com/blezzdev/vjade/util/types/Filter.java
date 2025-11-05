package com.blezzdev.vjade.util.types;

import static org.lwjgl.opengl.GL11C.*;

public enum Filter {
    LINEAR(GL_LINEAR, GL_LINEAR_MIPMAP_LINEAR), NEAREST(GL_NEAREST, GL_LINEAR_MIPMAP_NEAREST);

    public final int gl, mipmap;

    Filter(int gl, int mipmap) {
        this.gl = gl;
        this.mipmap = mipmap;
    }
}
