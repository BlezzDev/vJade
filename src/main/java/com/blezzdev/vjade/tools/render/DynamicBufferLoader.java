package com.blezzdev.vjade.tools.render;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.geometry.Geometry;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBVertexArrayObject.*;
import static org.lwjgl.opengl.GL20.*;

public class DynamicBufferLoader {
    protected int vbo, vao, ebo;
    protected FloatBuffer vertexBuffer;

    private int[] loadIndices() {
        int[] indices = new int[VJade.MAX_TEXTURE_CAPACITY / 4 * 6];
        for (int i = 0, j = 0; i < VJade.MAX_TEXTURE_CAPACITY; i += 4, j += 6) {
            indices[j] = i;
            indices[j + 1] = i + 1;
            indices[j + 2] = i + 2;
            indices[j + 3] = i + 2;
            indices[j + 4] = i + 3;
            indices[j + 5] = i;
        }

        return indices;
    }

    public void setupRectBuffers(Geometry geometry) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        int[] indices = loadIndices();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, VJade.MAX_TEXTURE_CAPACITY * VJade.VERTEX_SIZE * 4, GL_DYNAMIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, geometry.getBufferedIndexes(), GL_STATIC_DRAW);

        int stride = VJade.VERTEX_SIZE * Float.BYTES;

        // Position.
        glVertexAttribPointer(0, 3, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);

        // Texture coordinates.
        glVertexAttribPointer(1, 2, GL_FLOAT, false, stride, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        vertexBuffer = BufferUtils.createFloatBuffer(VJade.MAX_TEXTURE_CAPACITY * VJade.VERTEX_SIZE);
    }

    public void cleanup() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
        glDeleteBuffers(ebo);
    }
}
