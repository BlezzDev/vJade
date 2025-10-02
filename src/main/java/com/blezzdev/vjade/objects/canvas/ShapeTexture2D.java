package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.tools.VJade;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class ShapeTexture2D extends CanvasItem<ShapeTexture2D> {
    private int[] indexes;

    private int vao = 0;
    private int vbo = 0;
    private int ebo = 0;

    private Shape shape = Shape.RECTANGLE;

    public enum Shape {
        RECTANGLE,
        OVAL
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (VJade.existContext()) {
            GL20.glUseProgram(VJade.getContext().getLogic().getShaderProgram());

            Matrix4f model = new Matrix4f()
                    .translate(getPosition().x, getPosition().y, getzIndex())
                    .scale(getSize().x, getSize().y, 1f);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);

            int modelLoc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjModel");
            FloatBuffer fb = BufferUtils.createFloatBuffer(16);
            model.get(fb);
            GL20.glUniformMatrix4fv(modelLoc, false, fb);

            int loc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjDiffuseTex");
            glUniform1i(loc, 0);

            int useTexLoc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjUseTexture");
            glUniform1i(useTexLoc, 0);

            int modulateLoc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjModulate");
            glUniform4f(modulateLoc, getModulate().r1, getModulate().g1, getModulate().b1, getModulate().a1);

            GL30.glBindVertexArray(vao);
            GL11.glDrawElements(GL11.GL_TRIANGLES, indexes.length, GL11.GL_UNSIGNED_INT, 0);
            GL30.glBindVertexArray(0);
        }
    }

    private void buildGeometry(float[] vertices) {
        vao = GL30.glGenVertexArrays();
        vbo = GL15.glGenBuffers();
        ebo = GL15.glGenBuffers();

        GL30.glBindVertexArray(vao);

        // VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);

        // EBO
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ebo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexes, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 5 * Float.BYTES, 0);
        GL20.glEnableVertexAttribArray(0);

        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        GL20.glEnableVertexAttribArray(1);

        GL30.glBindVertexArray(0);
    }

    private void clean() {
        if (vbo != 0) {
            GL15.glDeleteBuffers(vbo);
            vbo = 0;
        }
        if (ebo != 0) {
            GL15.glDeleteBuffers(ebo);
            ebo = 0;
        }
        if (vao != 0) {
            GL30.glDeleteVertexArrays(vao);
            vao = 0;
        }
    }

    private void updateRenderer() {
        switch (shape) {
            case OVAL:
                break;
            default:
                indexes = new int[]{0, 1, 3, 1, 2, 3};
                buildGeometry(new float[]{
                        getPivot().x, getPivot().y, 0.0f,   1.0f, 1.0f,
                        getPivot().x, getPivot().y - 1, 0.0f,   1.0f, 0.0f,
                        getPivot().x - 1, getPivot().y - 1, 0.0f,   0.0f, 0.0f,
                        getPivot().x - 1, getPivot().y, 0.0f,   0.0f, 1.0f
                });
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        clean();
    }

    public ShapeTexture2D setShape(Shape shape) {
        clean();
        this.shape = shape;

        updateRenderer();

        return this;
    }

    public Shape getShape() {
        return shape;
    }
}
