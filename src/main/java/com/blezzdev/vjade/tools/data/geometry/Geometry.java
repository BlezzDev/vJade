package com.blezzdev.vjade.tools.data.geometry;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;

import java.util.ArrayList;
import java.util.List;

public class Geometry {
    private final List<Vertex> vertices = new ArrayList<>();

    public void newVertex(float x, float y, float z, float u, float v, Color color) {
        newVertex(x, y, z, u, v,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void newVertex(Vec3 position, float u, float v, Color color) {
        newVertex(position.x, position.y, position.z,
                u, v, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()
        );
    }

    public void newVertex(
            float x, float y, float z,
            float u, float v,
            float red, float green, float blue, float alpha)
    {
        vertices.add(new Vertex().setPosition(x, y, z)
                .setUV(u, v)
                .setColor(red, green, blue, alpha));
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public float[] getBuffer() {
        float[] vertexBuffer = new float[vertices.size() * VJade.VERTEX_SIZE];
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = vertices.get(i);
            for (int j = 0; j < VJade.VERTEX_SIZE; j++) {
                vertexBuffer[i * VJade.VERTEX_SIZE + j] = v.getBuffer()[j];
            }
        }

        return vertexBuffer;
    }

    public void clear() {
        vertices.clear();
    }
}