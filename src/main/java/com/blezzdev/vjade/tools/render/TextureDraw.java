package com.blezzdev.vjade.tools.render;

import com.blezzdev.vjade.objects.build.Shader;
import com.blezzdev.vjade.objects.canvas.CanvasItem;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.render.Texture;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

public class TextureDraw extends TextureLoader {
    public TextureDraw(Texture texture) {
        super(texture);
    }

    private Matrix4f loadModelMatrix(CanvasItem<?> canvas) {
        float width  = texture.getWidth() * canvas.getSize().x;
        float height = texture.getHeight() * canvas.getSize().y;

        float pivotOffsetX = width * canvas.getPivot().getX();
        float pivotOffsetY = height * canvas.getPivot().getY();

        return new Matrix4f()
                .translate(canvas.getPosition().x, canvas.getPosition().y, canvas.getzIndex())

                .translate(pivotOffsetX, pivotOffsetY, 0)

                .rotateZ((float) Math.toRadians(canvas.getRotation()))

                .translate(-pivotOffsetX, -pivotOffsetY, 0);
    }

    private Matrix4f loadPVMatrix() {
        Vec2 winSize = VJade.getContext().getSize();

        Matrix4f projection = new Matrix4f().ortho(0, winSize.x, winSize.y, 0, -1, 1);
        Matrix4f view = (VJade.getView() != null) ? VJade.getView().getViewMatrix() : new Matrix4f().identity();

        Matrix4f pv = new Matrix4f();
        projection.mul(view, pv);

        return pv;
    }

    private Matrix4f loadTransformMatrix(CanvasItem<?> canvas, Matrix4f model, Matrix4f pv) {
        switch (canvas.getSizeBehavior()) {
            case RELATIVE -> model.scale(texture.getWidth() * canvas.getSize().x, texture.getHeight() * canvas.getSize().y, 1);
            case FIXED -> model.scale(canvas.getSize().x, canvas.getSize().y, 1);
        }

        Matrix4f transform = new Matrix4f();
        pv.mul(model, transform);
        return transform;
    }

    private void callUniforms(Shader shader, FloatBuffer transformBuffer, Color modulateColor) {
        shader.setUniformMatrix4("vjTransform", transformBuffer);
        shader.setUniformColor("vjModulate", modulateColor);
        shader.setUniformBool("vjUseTexture", true);
        shader.setUniformInteger("vjDiffuseTex", 0);
    }

    private void enableMainTasks() {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture.getId());

        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void draw(CanvasItem<?> canvas) {
        Shader shader = loadShader(canvas);

        // Load transform data.
        Matrix4f pv = loadPVMatrix();
        Matrix4f model = loadModelMatrix(canvas);
        Matrix4f transform = loadTransformMatrix(canvas, model, pv);
        Color modulateColor = canvas.getModulate();

        FloatBuffer transformBuffer = BufferUtils.createFloatBuffer(16);
        transform.get(transformBuffer);

        callUniforms(shader, transformBuffer, modulateColor);
        enableMainTasks(); // Active textures, draw and quit.
    }

    private Shader loadShader(CanvasItem<?> canvas) {
        Shader shader = canvas.getShader();
        if (shader == null) {
            shader = VJade.getContext().getShader();
        }

        shader.bind();
        return shader;
    }
}
