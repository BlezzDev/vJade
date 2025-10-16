package com.blezzdev.vjade.tools.data.geometry;

public class Vertex {
    private final Vec3 position = new Vec3();
    private final float[] uv = new float[2];

    public Vertex setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
        return this;
    }

    public Vertex setUV(float u, float v) {
        uv[0] = u;
        uv[1] = v;
        return this;
    }

    public Vec3 getPosition() {
        return position;
    }

    public float[] getUV() {
        return uv;
    }

    public float[] getBuffer() {
        return new float[]{
                position.x, position.y, position.z, uv[0], uv[1]
        };
    }
}
