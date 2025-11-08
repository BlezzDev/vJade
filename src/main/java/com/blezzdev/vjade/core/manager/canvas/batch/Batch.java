package com.blezzdev.vjade.core.manager.canvas.batch;

import com.blezzdev.vjade.tools.canvas.Shader;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.canvas.Texture;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.*;
import com.blezzdev.vjade.util.types.Behavior;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.ARBVertexArrayObject.*;
import static org.lwjgl.opengl.GL15C.*;

public class Batch extends BufferLoader {
    private int indexCount = 0;
    private Texture currentTexture;
    private Shader currentShader;
    private final Geometry currentGeometry = new Geometry();
    private boolean drawing = false;
    private boolean textureBound = false;

    private final Vec2 transformSize = new Vec2();
    private final RenderCalculator calc = new RenderCalculator();
    private final float[] uvs = new float[4];
    private int currentVertexOffset = 0;

    public void begin() {
        // Init parameters.

        drawing = true;
        indexCount = 0;
        textureBound = false;
        currentVertexOffset = 0;

        if (vertexBuffer != null) {
            vertexBuffer.clear();
        }

        glBindVertexArray(vao);
    }

    public void draw(Shader shader, Texture texture, Vec3 position, Vec2 size, Pivot pivot, Color color, Behavior behavior, float rotation, float zIndex) {
        // Verify if a parameter needs a flush.

        if (shader != currentShader) {
            if (indexCount > 0) flush();

            if (shader == null) currentShader = VJade.getContext().getShader();
            else currentShader = shader;
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

        // Transform texture.

        calc.calculateUVs(texture.getFrame(), texture.getHorizontalDivisions(), texture.getVerticalDivisions(), uvs);
        calc.buildGeometry(position, transformSize, pivot, uvs, color, rotation, texture.getHorizontalFlip(), currentGeometry);
        float[] geometryBuffer = transformGeometry();

        // Update parameters.

        currentVertexOffset += geometryBuffer.length;
        indexCount += VJade.INDICES_PER_TEXTURE;
    }

    private float[] transformGeometry() {
        float[] geometryBuffer = currentGeometry.getBuffer();
        vertexBuffer.position(currentVertexOffset);
        vertexBuffer.put(geometryBuffer);

        return geometryBuffer;
    }

    public void end() {
        drawing = false;
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

        glDrawElements(GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);

        indexCount = 0;
        textureBound = false;
    }
}