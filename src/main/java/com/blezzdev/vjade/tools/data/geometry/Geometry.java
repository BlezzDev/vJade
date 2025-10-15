package com.blezzdev.vjade.tools.data.geometry;

import com.blezzdev.vjade.tools.VJade;

import java.util.ArrayList;
import java.util.List;

public class Geometry {
    private final List<Vertex> vertices = new ArrayList<>();
    private final List<Integer> indexes = new ArrayList<>();

    public void setIndexes(int... indexes) {
        for (int index : indexes) {
            this.indexes.add(index);
        }
    }

    public void newVertex(float x, float y, float z, float u, float v) {
        vertices.add(new Vertex().setPosition(x, y, z).setUV(u, v));
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public int[] getBufferedIndexes() {
        int[] indexBuffer = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {
            indexBuffer[i] = indexes.get(i);
        }

        return indexBuffer;
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
}
