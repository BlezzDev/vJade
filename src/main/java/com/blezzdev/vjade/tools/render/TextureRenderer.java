package com.blezzdev.vjade.tools.render;

import com.blezzdev.vjade.objects.build.Shader;
import com.blezzdev.vjade.objects.canvas.CanvasItem;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Geometry;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.render.Texture;
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

public class TextureRenderer extends BufferLoader {
    private int textureId;
    private Texture texture;

    public TextureRenderer(Texture texture) {
        this.texture = texture;
    }

    private Matrix4f loadGlobalSpaceData(CanvasItem<?> canvas) {
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

    public void loadTexGeometry(boolean flip, int frameIndex, int cols, int rows) {
        Geometry geometry = new Geometry();

        float frameWidth = 1f / (cols + 1);
        float frameHeight = 1f / (rows + 1);

        int frameX = frameIndex % (cols + 1);
        int frameY = frameIndex / (cols + 1);

        float u0 = frameX * frameWidth;
        float v0 = frameY * frameHeight;
        float u1 = u0 + frameWidth;
        float v1 = v0 + frameHeight;

        if (flip) {
            geometry.newVertex(-0.5f, 0.5f, 0, u1, v1);
            geometry.newVertex(0.5f, 0.5f, 0, u0, v1);
            geometry.newVertex(0.5f, -0.5f, 0, u0, v0);
            geometry.newVertex(-0.5f, -0.5f, 0, u1, v0);
        } else {
            geometry.newVertex(0.5f, 0.5f, 0, u1, v1);
            geometry.newVertex(-0.5f, 0.5f, 0, u0, v1);
            geometry.newVertex(-0.5f, -0.5f, 0, u0, v0);
            geometry.newVertex(0.5f, -0.5f, 0, u1, v0);
        }

        geometry.setIndexes(0, 1, 2, 2, 3, 0);

        setupRectBuffers(geometry);
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
        Vec2 winSize = VJade.getContext().getSize();

        Matrix4f projection = new Matrix4f().ortho(0, winSize.x, winSize.y, 0, -1, 1);
        Matrix4f view = (VJade.getView() != null) ? VJade.getView().getViewMatrix() : new Matrix4f().identity();

        Matrix4f pv = new Matrix4f();
        projection.mul(view, pv);

        Matrix4f model = loadGlobalSpaceData(canvas);

        switch (canvas.getSizeBehavior()) {
            case RELATIVE -> model.scale(texture.getWidth() * canvas.getSize().x, texture.getHeight() * canvas.getSize().y, 1);
            case FIXED -> model.scale(canvas.getSize().x, canvas.getSize().y, 1);
        }

        Matrix4f transform = new Matrix4f();
        pv.mul(model, transform);

        Color modulateColor = canvas.getModulate();
        FloatBuffer transformBuffer = BufferUtils.createFloatBuffer(16);
        transform.get(transformBuffer);

        shader.setUniformMatrix4fv("vjTransform", transformBuffer);
        shader.setUniformFloat("vjModulate",
                modulateColor.getRed(),
                modulateColor.getGreen(),
                modulateColor.getBlue(),
                modulateColor.getAlpha()
        );
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
