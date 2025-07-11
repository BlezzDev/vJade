package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.Vector2;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Sprite extends Node{
    private int textureId;
    private Vector2 size;
    private Vector2 scale;

    public Sprite() { this("assets/null.png", 0, 0); }
    public Sprite(int x, int y) { this("assets/null.png", x, y); }
    public Sprite(String path) { this(path, 0, 0); }
    public Sprite(String path, int x, int y) { super(x, y); loadTexture(path); }

    public void loadTexture(String path) {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer compBuffer = BufferUtils.createIntBuffer(1);

        stbi_set_flip_vertically_on_load(false);
        ByteBuffer image = stbi_load(path, widthBuffer, heightBuffer, compBuffer, 4);

        if (image == null) {
            throw new RuntimeException("Error loading texture: " + stbi_failure_reason());
        }

        size = new Vector2(widthBuffer.get(), heightBuffer.get());

        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int) size.x, (int) size.y, 0,
                GL_RGBA, GL_UNSIGNED_BYTE, image);

        stbi_image_free(image);
    }

    @Override
    public void render() {
        super.render();

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex2f(position.x, position.y);
        glTexCoord2f(1, 0); glVertex2f(position.x + size.x, position.y);
        glTexCoord2f(1, 1); glVertex2f(position.x + size.x, position.y + size.y);
        glTexCoord2f(0, 1); glVertex2f(position.x, position.y + size.y);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    @Override
    public void destroy() {
        super.destroy();

        glDeleteTextures(textureId);
    }

    public Vector2 getSize() { return size; }
    public Vector2 getScale() { return scale; }

    public void setScale(Vector2 scale) { this.scale = scale; glScalef(this.scale.x, this.scale.y, 1.0f); }
    public void setFilter(byte filter) {
        switch (filter) {
            case 0:
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
                break;
            case 1:
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                break;
        }
    }
}
