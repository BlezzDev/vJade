package com.blezzdev.vjade.tools.render;

import com.blezzdev.vjade.core.exceptions.texture.NullTextureException;
import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.tools.Vector2;
import com.blezzdev.vjade.tools.render.shape.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureRenderer {
    private Window window;

    public TextureRenderer(Window window) {
        this.window = window;
    }

    public void draw(ShapeRender shape) { shape.render(); }
    public void draw(int textureID, Vector2 position, Vector2 size) {
        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL_QUADS);
        glTexCoord2f(1, 0); glVertex2f(position.getX() + size.getX(), position.getY());
        glTexCoord2f(1, 1); glVertex2f(position.getX() + size.getX(), position.getY() + size.getY());
        glTexCoord2f(0, 1); glVertex2f(position.getX(),     position.getY() + size.getY());
        glTexCoord2f(0, 0); glVertex2f(position.getX(),     position.getY());
        glEnd();
    }
    public int loadTexture(String path) {
        IntBuffer width  = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer image = STBImage.stbi_load(path, width, height, channels, 4);
        if (image == null) {
            throw new NullTextureException("The texture '" + path + "' is null.");
        }

        int texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0),
                0, GL_RGBA, GL_UNSIGNED_BYTE, image);

        STBImage.stbi_image_free(image);
        return texID;
    }
}
