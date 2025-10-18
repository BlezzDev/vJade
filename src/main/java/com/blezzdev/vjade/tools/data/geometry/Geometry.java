package com.blezzdev.vjade.tools.data.geometry;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;

import java.nio.FloatBuffer;
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

    public void newVertex(Vec3 position, Vec2 texCoordinates, Color color) {
        newVertex(new float[]{
                position.x, position.y, position.z,
                texCoordinates.x, texCoordinates.y,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()
        });
    }
    public void newVertex(float[] buffers) {
        vertices.add(new Vertex()
                .setPosition(buffers[0], buffers[1], buffers[2])
                .setUV(buffers[3], buffers[4])
                .setColor(buffers[5], buffers[6], buffers[7], buffers[8])
        );
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
