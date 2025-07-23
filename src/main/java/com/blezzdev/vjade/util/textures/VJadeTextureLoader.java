package com.blezzdev.vjade.util.textures;

import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.ColorHSV;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class VJadeTextureLoader {
    public int textureId;
    public Vector2 size;

    public VJadeTextureLoader(String path) { load(path); }

    public void load(String path) {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer compBuffer = BufferUtils.createIntBuffer(1);

        stbi_set_flip_vertically_on_load(false);

        ByteBuffer image = null;
        boolean loadedFromFile = false;

        if (path != null && !path.trim().isEmpty()) {
            image = stbi_load(path, widthBuffer, heightBuffer, compBuffer, 4);
            if (image != null) {
                loadedFromFile = true;
            }
        }

        if (!loadedFromFile) {
            Vector2 tempSize = Vector2.ONE.multiply(32);
            image = new VJadeTextureShapeLoader.RectangleShapeRenderer().fillRect(tempSize, ColorHSV.RED.toRGBColor());
            size = tempSize;
        } else {
            size = new Vector2(widthBuffer.get(), heightBuffer.get());
        }

        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, (int) size.x, (int) size.y, 0,
                GL_RGBA, GL_UNSIGNED_BYTE, image);

        if (loadedFromFile) {
            stbi_image_free(image);
        }
    }
}
