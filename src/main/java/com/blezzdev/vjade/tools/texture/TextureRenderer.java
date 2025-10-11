package com.blezzdev.vjade.tools.texture;

import com.blezzdev.vjade.objects.build.Shader;
import com.blezzdev.vjade.objects.canvas.CanvasItem;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vector2;
import com.blezzdev.vjade.util.types.Filter;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;
import static org.lwjgl.opengl.GL30C.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class TextureRenderer extends Renderer {
    private int textureId;
    private Texture texture;

    public TextureRenderer(Texture texture) {
        this.texture = texture;
    }

    public void loadTexGeometry(Vector2 pivot, boolean flip) {
        float[] vertices;

        if (flip) {
            vertices = new float[]{
                    pivot.x, pivot.y + 1, 0.0f, 1.0f, 1.0f,
                    pivot.x + 1, pivot.y + 1, 0.0f, 0.0f, 1.0f,
                    pivot.x + 1, pivot.y, 0.0f, 0.0f, 0.0f,
                    pivot.x, pivot.y, 0.0f, 1.0f, 0.0f
            };
        } else {
            vertices = new float[]{
                    pivot.x + 1, pivot.y + 1, 0.0f, 1.0f, 1.0f,
                    pivot.x, pivot.y + 1, 0.0f, 0.0f, 1.0f,
                    pivot.x, pivot.y, 0.0f, 0.0f, 0.0f,
                    pivot.x + 1, pivot.y, 0.0f, 1.0f, 0.0f
            };
        }

        int[] indexes = {0, 1, 2, 2, 3, 0};

        setupTexBuffers(vertices, indexes);
    }

    public void loadTexture(Filter filter, boolean flip_v) {
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter.getGl());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter.getGl());

        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            stbi_set_flip_vertically_on_load(flip_v);
            ByteBuffer image = stbi_load(texture.getResourcePath(), width, height, channels, 0);

            if (image != null) {
                int format = channels.get(0) == 4 ? GL_RGBA : GL_RGB;
                glTexImage2D(GL_TEXTURE_2D, 0, format, width.get(0), height.get(0),
                        0, format, GL_UNSIGNED_BYTE, image);
                glGenerateMipmap(GL_TEXTURE_2D);
                stbi_image_free(image);
            }
        }

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void draw(CanvasItem<?> canvas) {
        Shader shader = canvas.getShader();

        if (shader == null) {
            shader = VJade.getContext().getShader();
        }

        shader.bind();

        Vector2 winSize = VJade.getContext().getSize();

        Matrix4f projection = new Matrix4f()
                .ortho(0, winSize.x, winSize.y, 0, -1, 1);

        Matrix4f model = new Matrix4f()
                .translate(canvas.getPosition().x, canvas.getPosition().y, canvas.getzIndex())
                .scale(texture.getWidth() * canvas.getSize().x, texture.getHeight() * canvas.getSize().y, 1);

        switch (canvas.getSizeBehavior()) {
            case FIXED -> model.scale(canvas.getSize().x, canvas.getSize().y, 1);
        }

        Color modulateColor = canvas.getModulate();

        FloatBuffer projectionBuffer = BufferUtils.createFloatBuffer(16);
        FloatBuffer modelBuffer = BufferUtils.createFloatBuffer(16);

        projection.get(projectionBuffer);
        model.get(modelBuffer);

        shader.setUniformMatrix4fv("vjProjection", projectionBuffer);
        shader.setUniformMatrix4fv("vjModel", modelBuffer);
        shader.setUniformFloat("vjModulate", modulateColor.getRed(), modulateColor.getGreen(), modulateColor.getBlue(), modulateColor.getAlpha());
        shader.setUniformBool("vjUseTexture", true);
        shader.setUniformInteger("vjDiffuseTex", 0);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void cleanup() {
        if (textureId != 0) {
            glDeleteTextures(textureId);
            textureId = 0;
        }
    }
}
