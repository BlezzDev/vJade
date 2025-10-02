package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.geometry.Vector2;
import com.blezzdev.vjade.tools.types.Filter;
import com.blezzdev.vjade.tools.texture.Texture;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Texture2D extends CanvasItem<Texture2D> {
    private int[] indexes;

    private float frameWidth, frameHeight;

    private int vao = 0;
    private int vbo = 0;
    private int ebo = 0;

    private int textureId = 0;
    private Texture texture;
    private int filter = Filter.LINEAR;
    private Vector2 frameCoordinates = new Vector2();
    private Vector2 frameDivisions = new Vector2();

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (VJade.existContext()) {
            GL20.glUseProgram(VJade.getContext().getLogic().getShaderProgram());

            Matrix4f model = new Matrix4f()
                    .translate(getPosition().x, getPosition().y, 0f)
                    .scale(getTexture().getWidth() * getSize().x, getTexture().getHeight() * getSize().y, 1f);

            switch (behavior) {
                case FIXED:
                    model = new Matrix4f()
                            .translate(getPosition().x, getPosition().y, getzIndex())
                            .scale(getSize().x, getSize().y, 1f);
            }

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);

            int modelLoc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjModel");
            FloatBuffer fb = BufferUtils.createFloatBuffer(16);
            model.get(fb);
            GL20.glUniformMatrix4fv(modelLoc, false, fb);

            int loc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjDiffuseTex");
            glUniform1i(loc, 0);

            int useTexLoc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjUseTexture");
            glUniform1i(useTexLoc, 1);

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

            int modulateLoc = glGetUniformLocation(VJade.getContext().getLogic().getShaderProgram(), "vjModulate");
            glUniform4f(modulateLoc, getModulate().r1, getModulate().g1, getModulate().b1, getModulate().a1);

            GL30.glBindVertexArray(vao);
            GL11.glDrawElements(GL11.GL_TRIANGLES, indexes.length, GL11.GL_UNSIGNED_INT, 0);
            GL30.glBindVertexArray(0);
        }
    }

    private void loadTextureData() {
        float u0 = (frameCoordinates.x * frameWidth) / getTexture().getWidth();
        float v0 = (frameCoordinates.y * frameHeight) / getTexture().getHeight();
        float u1 = ((frameCoordinates.x + 1) * frameWidth) / getTexture().getWidth();
        float v1 = ((frameCoordinates.y + 1) * frameHeight) / getTexture().getHeight();

        float[] vertices = {
                getPivot().x, getPivot().y, 0.0f,   u1, v1,
                getPivot().x, getPivot().y - 1, 0.0f,   u1, v0,
                getPivot().x - 1, getPivot().y - 1, 0.0f,   u0, v0,
                getPivot().x - 1, getPivot().y, 0.0f,   u0, v1
        };

        indexes = new int[]{0, 1, 3, 1, 2, 3};
        buildGeometry(vertices);
    }

    private void loadTexture() {
        textureId = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, filter);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, filter);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer image = STBImage.stbi_load(texture.getResourcePath(), width, height, channels, 4);
        if (image != null) {
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width.get(0), height.get(0), 0,
                    GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        }

        assert image != null;
        STBImage.stbi_image_free(image);
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

    private void updateUVs() {
        float u0 = (frameCoordinates.x * frameWidth) / getTexture().getWidth();
        float v0 = (frameCoordinates.y * frameHeight) / getTexture().getHeight();
        float u1 = ((frameCoordinates.x + 1) * frameWidth) / getTexture().getWidth();
        float v1 = ((frameCoordinates.y + 1) * frameHeight) / getTexture().getHeight();

        float[] vertices = {
                getPivot().x, getPivot().y, 0.0f,   u1, v1,
                getPivot().x, getPivot().y - 1, 0.0f,   u1, v0,
                getPivot().x - 1, getPivot().y - 1, 0.0f,   u0, v0,
                getPivot().x - 1, getPivot().y, 0.0f,   u0, v1
        };

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, vertices);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }


    @Override
    public void finish() {
        super.finish();

        clean();
    }

    private void clean() {
        if (textureId != 0) {
            GL11.glDeleteTextures(textureId);
            textureId = 0;
        }
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

    public Texture2D setFrameCoordinates(int x, int y) { setFrameCoordinates(new Vector2(x, y)); return this; }
    public Texture2D setFrameCoordinates(Vector2 frameCoordinates) {
        this.frameCoordinates = frameCoordinates;

        updateUVs();

        return this;
    }

    public Texture2D setTexture(Texture texture) {
        clean();
        this.texture = texture;

        frameWidth = getTexture().getWidth() / getFrameDivisions().x;
        frameHeight = getTexture().getHeight() / getFrameDivisions().y;

        loadTextureData();
        loadTexture();

        return this;
    }

    public Texture2D setFrameDivisions(float x, float y) { setFrameDivisions(new Vector2(x, y)); return this; }
    public Texture2D setFrameDivisions(Vector2 divisions) {
        this.frameDivisions = divisions;
        return this;
    }

    public Texture2D setFrame(int frame) {
        int totalFrames = (int) (getFrameDivisions().x * getFrameDivisions().y);

        if (frame < 0) frame = 0;
        if (frame >= totalFrames) frame = totalFrames - 1;

        int x = (int) (frame % getFrameDivisions().x);
        int y = (int) (frame / getFrameDivisions().x);

        setFrameCoordinates(new Vector2(x, y));

        return this;
    }


    public Texture2D setFilter(int filter) {
        this.filter = filter;
        return this;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getFrameHeight() {
        return frameHeight;
    }

    public float getFrameWidth() {
        return frameWidth;
    }

    public Vector2 getFrameDivisions() { return frameDivisions; }

    public Vector2 getFrameCoordinates() { return frameCoordinates; }
}