package com.blezzdev.vjade.nodes;

import com.blezzdev.vjade.util.VJadeVector2;
import com.blezzdev.vjade.util.color.VJadeColorHSV;
import com.blezzdev.vjade.util.textures.VJadeTextureLoader;
import com.blezzdev.vjade.util.textures.VJadeTextureRenderer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

public class VJadeSprite extends VJadeVisualNode {
    private int textureId;
    private VJadeVector2 size;
    private VJadeVector2 scale;

    public VJadeSprite() { this("", 0, 0, VJadeColorHSV.RED); }
    public VJadeSprite(int x, int y) { this("", x, y, VJadeColorHSV.RED); }
    public VJadeSprite(String path) { this(path, 0, 0, VJadeColorHSV.RED); }
    public VJadeSprite(VJadeColorHSV color) { this("", 0, 0, color); }
    public VJadeSprite(String path, int x, int y, VJadeColorHSV color) {
        super(x, y);

        VJadeTextureLoader t = new VJadeTextureLoader(path, color);
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

    public VJadeVector2 getSize() { return size; }
    public VJadeVector2 getScale() { return scale; }

    public void setScale(VJadeVector2 scale) { this.scale = scale; }
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
