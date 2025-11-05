package com.blezzdev.vjade.tools.canvas;

import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.util.types.Filter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL30C.glGenerateMipmap;

class ImageLoader {
    int id;

    private void updateParameters(IntBuffer width, IntBuffer height, Vec2 size) {
        id = GL11.glGenTextures();
        size.set(width.get(), height.get());
    }

    private void loadTextureData(IntBuffer width, IntBuffer height, IntBuffer channels, Filter filter, ByteBuffer image) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, filter.mipmap);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, filter.gl);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL13.GL_TEXTURE_COMPRESSED, GL11.GL_TRUE);

        if (image != null) {
            int format = channels.get(0) == 4 ? GL11.GL_RGBA : GL11.GL_RGB;

            GL11.glTexImage2D(
                    GL11.GL_TEXTURE_2D, 0, format,
                    width.get(0), height.get(0),
                    0, format, GL11.GL_UNSIGNED_BYTE, image
            );
            glGenerateMipmap(GL11.GL_TEXTURE_2D);

            IntBuffer compressed = MemoryStack.stackMallocInt(1);
            GL11.glGetTexLevelParameteriv(GL11.GL_TEXTURE_2D, 0, GL13.GL_TEXTURE_COMPRESSED, compressed);
            STBImage.stbi_image_free(image);
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void load(String resourcePath, Vec2 size, Filter filter) { load(resourcePath, size, filter, false); }
    public void load(String resourcePath, Vec2 size, Filter filter, boolean verticalFlip) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            STBImage.stbi_set_flip_vertically_on_load(verticalFlip);

            ByteBuffer image = STBImage.stbi_load(resourcePath, width, height, channels, 0);

            updateParameters(width, height, size);
            loadTextureData(width, height, channels, filter, image);
        }
    }

    public void destroy() {
        GL11.glDeleteTextures(id);
    }
}
