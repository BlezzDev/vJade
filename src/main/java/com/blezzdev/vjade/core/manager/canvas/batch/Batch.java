package com.blezzdev.vjade.core.manager.canvas.batch;

import com.blezzdev.vjade.tools.canvas.Shader;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.canvas.Texture;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.*;
import com.blezzdev.vjade.tools.data.geometry.specialized.Geometry;
import com.blezzdev.vjade.tools.data.geometry.specialized.Pivot;
import com.blezzdev.vjade.util.types.Behavior;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.ARBVertexArrayObject.*;
import static org.lwjgl.opengl.GL15C.*;

public class Batch extends BufferLoader {
    private int indexCount = 0;
    private Texture currentTexture;
    private Shader currentShader;
    private final Geometry currentGeometry = new Geometry();
    private boolean textureBound = false;

    private final Vec2 transformSize = new Vec2();
    private final RenderCalculator calc = new RenderCalculator();
    private final float[] uvs = new float[4];

    public void begin() {
        // Init parameters.

        indexCount = 0;
        textureBound = false;

        if (vertexBuffer != null) {
            vertexBuffer.clear();
        }

        glBindVertexArray(vao);
    }

    public void draw(Shader shader, Texture texture, Vec2 position, Vec2 size, Pivot pivot, Color color, Behavior behavior, float rotation, float zIndex, float zoom, Vec2 view) {
        // Verify if a parameter needs a flush.

        if (shader != currentShader) {
            if (indexCount > 0) flush();
            currentShader = (shader == null) ? VJade.getContext().getShader() : shader;
        }

        if (!textureBound || texture != currentTexture) {
            if (indexCount > 0) flush();
            currentTexture = texture;
            textureBound = true;
        }

        if (vertexBuffer.remaining() < VJade.VERTICES_PER_TEXTURE * VJade.VERTEX_SIZE) {
            flush();
        }

        transformSize.set(size);
        if (behavior == Behavior.RELATIVE) {
            transformSize.multiply(texture.getSize().x, texture.getSize().y);
        }

        currentGeometry.clear();

        // Transform texture.

        calc.calculateUVs(texture.getFrame(), texture.getHorizontalDivisions(), texture.getVerticalDivisions(), uvs);
        calc.buildGeometry(new Vec3(position.x, position.y, zIndex), transformSize, pivot, uvs, color, rotation, texture.getHorizontalFlip(), zoom, view, currentGeometry);

        vertexBuffer.put(currentGeometry.getBuffer());
        indexCount += VJade.INDICES_PER_TEXTURE;
    }

    public void end() {
        flush();
    }

    private void flush() {
        if (indexCount == 0) return;
        if (currentShader == null) currentShader = VJade.getContext().getShader();

        currentShader.bind();

        if (currentTexture != null) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, currentTexture.getId());

            currentShader.setUniformBool("fUseTexture", true);
            currentShader.setUniformInteger("fDiffuseTex", 0);
        } else {
            currentShader.setUniformBool("fUseTexture", false);
        }

        vertexBuffer.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertexBuffer);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDrawElements(GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);

        vertexBuffer.clear();
        indexCount = 0;
        textureBound = false;
    }
}