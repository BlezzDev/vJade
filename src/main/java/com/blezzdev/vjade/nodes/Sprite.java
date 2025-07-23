package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.ColorHSV;
import com.blezzdev.vjade.util.textures.VJadeTextureLoader;
import com.blezzdev.vjade.util.textures.VJadeTextureRenderer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

public class Sprite extends VNode {
    private int textureId;
    private Vector2 size;
    private Vector2 scale = new Vector2(1, 1);

    public Sprite() { this("", 0, 0); }
    public Sprite(int x, int y) { this("", x, y); }
    public Sprite(String path) { this(path, 0, 0); }
    public Sprite(String path, Vector2 position) { this(path, (int) (position.x), (int) (position.y)); }
    public Sprite(String path, int x, int y) {
        super(x, y);

        VJadeTextureLoader t = new VJadeTextureLoader(path);
        this.textureId = t.textureId;
        this.size = t.size;
    }

    @Override
    public void render() {
        super.render();

        glPushMatrix();

        glTranslatef(position.x, position.y, 0);
        glScalef(scale.x, scale.y, 1.0f);

        VJadeTextureRenderer.render(textureId, position, size);

        glPopMatrix();
    }

    @Override
    public void destroy() {
        super.destroy();
        VJadeTextureRenderer.destroy(textureId);
    }

    public Vector2 getSize() { return size; }
    public Vector2 getScale() { return scale; }

    public void setScale(float scale) { setScale(Vector2.ONE.multiply((int) scale)); }
    public void setScale(float width, float height) { setScale(new Vector2(width, height)); }
    public void setScale(Vector2 scale) { this.scale = scale; }
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
