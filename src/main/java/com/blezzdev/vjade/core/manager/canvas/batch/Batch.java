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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    private boolean enableDepth = false;
    private final List<DrawCommand> commands = new ArrayList<>();

    public void begin() {
        indexCount = 0;
        textureBound = false;
        commands.clear();

        if (vertexBuffer != null) vertexBuffer.clear();
        glBindVertexArray(vao);

        if (enableDepth) {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }

    public void setDepthEnabled(boolean enabled) {
        this.enableDepth = enabled;
    }

    public boolean isDepthEnabled() {
        return enableDepth;
    }

    public void draw(Shader shader, Texture texture, Vec2 position, Vec2 size,
                     Pivot pivot, Color color, Behavior behavior, float rotation,
                     float zIndex, float zoom, Vec2 view) {

        DrawCommand cmd = new DrawCommand(shader, texture, position, size, pivot,
                color, behavior, rotation, zIndex, zoom, view);
        commands.add(cmd);
    }

    public void end() {
        if (!enableDepth) {
            commands.sort(Comparator.comparingDouble(c -> c.zIndex));
        }

        for (DrawCommand cmd : commands) {
            renderCommand(cmd);
        }

        flush();
    }

    private void renderCommand(DrawCommand cmd) {
        Shader shader = (cmd.shader == null) ? VJade.getContext().getShader() : cmd.shader;
        Texture texture = cmd.texture;

        if (shader != currentShader) {
            if (indexCount > 0) flush();
            currentShader = shader;
        }

        if (!textureBound || texture != currentTexture) {
            if (indexCount > 0) flush();
            currentTexture = texture;
            textureBound = true;
        }

        if (vertexBuffer.remaining() < VJade.VERTICES_PER_TEXTURE * VJade.VERTEX_SIZE) {
            flush();
        }

        transformSize.set(cmd.size);
        if (cmd.behavior == Behavior.RELATIVE) {
            transformSize.multiply(texture.getSize().x, texture.getSize().y);
        }

        currentGeometry.clear();

        calc.calculateUVs(texture.getFrame(), texture.getHorizontalDivisions(),
                texture.getVerticalDivisions(), uvs);

        calc.buildGeometry(new Vec3(cmd.position.x, cmd.position.y, cmd.zIndex),
                transformSize, cmd.pivot, uvs, cmd.color, cmd.rotation,
                texture.getHorizontalFlip(), cmd.zoom, cmd.view, currentGeometry);

        vertexBuffer.put(currentGeometry.getBuffer());
        indexCount += VJade.INDICES_PER_TEXTURE;
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

        glDrawElements(GL11.GL_TRIANGLES, indexCount, GL_UNSIGNED_INT, 0);

        vertexBuffer.clear();
        indexCount = 0;
        textureBound = false;
    }
}