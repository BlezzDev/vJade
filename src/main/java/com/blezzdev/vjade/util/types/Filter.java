package com.blezzdev.vjade.util.types;

import static org.lwjgl.opengl.GL11C.GL_LINEAR;
import static org.lwjgl.opengl.GL11C.GL_NEAREST;

public enum Filter {
    LINEAR(GL_LINEAR), NEAREST(GL_NEAREST);

    final int gl;

    Filter(int gl) {
        this.gl = gl;
    }

    public int getGl() {
        return gl;
    }
}
