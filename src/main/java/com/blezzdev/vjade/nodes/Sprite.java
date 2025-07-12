package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.VJadeColorHSV;
import com.blezzdev.vjade.util.textures.TextureLoader;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

public class Sprite extends Node {
    private int textureId;
    private Vector2 size;
    private Vector2 scale;

    public Sprite() { this("", 0, 0, VJadeColorHSV.RED); }
    public Sprite(int x, int y) { this("", x, y, VJadeColorHSV.RED); }
    public Sprite(String path) { this(path, 0, 0, VJadeColorHSV.RED); }
    public Sprite(VJadeColorHSV color) { this("", 0, 0, color); }
    public Sprite(String path, int x, int y, VJadeColorHSV color) {
        super(x, y);

        TextureLoader textureLoader = new TextureLoader(path, color);
        this.textureId = textureLoader.textureId;
        this.size = textureLoader.size;
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
