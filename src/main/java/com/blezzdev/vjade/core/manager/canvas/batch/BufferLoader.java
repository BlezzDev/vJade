package com.blezzdev.vjade.core.manager.canvas.batch;

import com.blezzdev.vjade.tools.VJade;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL11C.GL_FLOAT;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

class BufferLoader {
    int vao, vbo, ebo;
    FloatBuffer vertexBuffer;
    IntBuffer indexBuffer;

    static final int VERTEX_SIZE_BYTES = VJade.VERTEX_SIZE * Float.BYTES;
    static final int SPRITE_SIZE_BYTES = VJade.VERTICES_PER_TEXTURE * VERTEX_SIZE_BYTES;
    static final int BUFFER_SIZE = VJade.MAX_TEXTURE_CAPACITY * SPRITE_SIZE_BYTES;

    public BufferLoader() {
        setupBuffers();
    }

    private void loadLayouts() {
        int stride = VERTEX_SIZE_BYTES;

        glVertexAttribPointer(0, 3, GL_FLOAT, false, stride, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, stride, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 4, GL_FLOAT, false, stride, 5 * Float.BYTES);
        glEnableVertexAttribArray(2);
    }

    private void setupIndices() {
        int totalIndices = VJade.MAX_TEXTURE_CAPACITY * VJade.INDICES_PER_TEXTURE;
        indexBuffer = MemoryUtil.memAllocInt(totalIndices);

        for (int i = 0; i < VJade.MAX_TEXTURE_CAPACITY; i++) {
            int offset = i * 4;
            indexBuffer.put(offset);
            indexBuffer.put(offset + 1);
            indexBuffer.put(offset + 2);
            indexBuffer.put(offset + 2);
            indexBuffer.put(offset + 3);
            indexBuffer.put(offset);
        }
        indexBuffer.flip();
    }

    public void setupBuffers() {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BUFFER_SIZE, GL_DYNAMIC_DRAW);

        loadLayouts();
        setupIndices();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(indexBuffer);

        int vertexCapacity = VJade.MAX_TEXTURE_CAPACITY * VJade.VERTICES_PER_TEXTURE * VJade.VERTEX_SIZE;
        vertexBuffer = MemoryUtil.memAllocFloat(vertexCapacity);
    }
}
