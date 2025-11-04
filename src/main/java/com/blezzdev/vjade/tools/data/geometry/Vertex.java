package com.blezzdev.vjade.tools.data.geometry;

public class Vertex {
    private final float[] position = new float[3];
    private final float[] uv = new float[2];
    private final float[] color = new float[4];

    public Vertex setPosition(float x, float y, float z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
        return this;
    }

    public Vertex setUV(float u, float v) {
        uv[0] = u;
        uv[1] = v;
        return this;
    }

    public Vertex setColor(float r, float g, float b, float a) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
        color[3] = a;
        return this;
    }

    public float[] getPosition() {
        return position;
    }

    public float[] getUV() {
        return uv;
    }

    public float[] getColor() {
        return color;
    }

    public float[] getBuffer() {
        return new float[]{
                position[0], position[1], position[2], uv[0], uv[1],
                color[0], color[1], color[2], color[3]
        };
    }
}
