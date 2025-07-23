package com.blezzdev.vjade.util.textures;

import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.ColorRGB;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

public class VJadeTextureShapeRenderer {
    public int textureId;

    public ByteBuffer fillRect(Vector2 size, ColorRGB color) {
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
        return panel;
    }
}
