package com.blezzdev.vjade.gui;

import com.blezzdev.vjade.nodes.VJadeVisualNode;
import com.blezzdev.vjade.util.VJadeUIDirection;
import com.blezzdev.vjade.util.VJadeVector2;
import com.blezzdev.vjade.util.color.VJadeColorRGB;
import com.blezzdev.vjade.util.textures.VJadeTextureRenderer;
import com.blezzdev.vjade.util.textures.VJadeTextureShapeLoader;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

public class VJadeUIPanel extends VJadeVisualNode {
    private VJadeVector2 size;
    private VJadeUIDirection direction;
    private VJadeColorRGB color;
    private boolean visible;
    private int textureId;

    private final ByteBuffer panel;

    public VJadeUIPanel(VJadeVector2 size, VJadeColorRGB color) {
        this.size = size;
        this.color = color;
        textureId = glGenTextures();

        ByteBuffer panel = new VJadeTextureShapeLoader.RectangleShapeRenderer().fillRect(size, color);
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int) size.x, (int) size.y, 0,
                GL_RGBA, GL_UNSIGNED_BYTE, panel);

        this.panel = panel;
    }

    @Override
    public void render() {
        super.render();
        VJadeTextureRenderer.render(textureId, position, size);
    }

    @Override
    public void destroy() {
        super.destroy();
        VJadeTextureRenderer.destroy(panel.asIntBuffer());
    }

    public void setSize(VJadeVector2 size) { this.size = size; }
    public void setDirection(VJadeUIDirection direction) { this.direction = direction; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setColor(VJadeColorRGB color) { this.color = color; }

    public VJadeVector2 getSize() { return size; }
    public VJadeUIDirection getDirection() { return direction; }
    public VJadeColorRGB getColor() { return color; }
    public boolean getVisible() { return visible; }
}
