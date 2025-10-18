package com.blezzdev.vjade.tools.render;

import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Geometry;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.geometry.Vec3;
import com.blezzdev.vjade.tools.data.render.Texture;
import com.blezzdev.vjade.util.types.Filter;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30C.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class TextureLoader extends BufferLoader {
    protected Texture texture;

    public TextureLoader(Texture texture) {
        this.texture = texture;
    }

    public void loadTexGeometry(Color color, boolean flip, int frameIndex, int cols, int rows) {
        Geometry geometry = new Geometry();

        float frameWidth = 1f / (cols + 1);
        float frameHeight = 1f / (rows + 1);

        int frameX = frameIndex % (cols + 1);
        int frameY = frameIndex / (cols + 1);

        float u0 = frameX * frameWidth;
        float v0 = frameY * frameHeight;
        float u1 = u0 + frameWidth;
        float v1 = v0 + frameHeight;

        if (flip) {
            geometry.newVertex(new Vec3(-0.5f, 0.5f, 0), new Vec2(u1, v1), color);
            geometry.newVertex(new Vec3(0.5f, 0.5f, 0), new Vec2(u0, v1), color);
            geometry.newVertex(new Vec3(0.5f, -0.5f, 0), new Vec2(u0, v0), color);
            geometry.newVertex(new Vec3(-0.5f, -0.5f, 0), new Vec2(u1, v0), color);
        } else {
            geometry.newVertex(new Vec3(0.5f, 0.5f, 0), new Vec2(u1, v1), color);
            geometry.newVertex(new Vec3(-0.5f, 0.5f, 0), new Vec2(u0, v1), color);
            geometry.newVertex(new Vec3(-0.5f, -0.5f, 0), new Vec2(u0, v0), color);
            geometry.newVertex(new Vec3(0.5f, -0.5f, 0), new Vec2(u1, v0), color);
        }

        geometry.setIndexes(0, 1, 2, 2, 3, 0);

        setupRectBuffers(geometry);
    }

    public void loadTexture(Filter filter, boolean flip_v) {
        texture.enableTexture();
        glBindTexture(GL_TEXTURE_2D, texture.getId());

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter.getGl());
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter.getGl());

        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            stbi_set_flip_vertically_on_load(flip_v);
            ByteBuffer image = stbi_load(texture.getResourcePath(), width, height, channels, 0);

            if (image != null) {
                int format = channels.get(0) == 4 ? GL_RGBA : GL_RGB;
                glTexImage2D(GL_TEXTURE_2D, 0, format, width.get(0), height.get(0),
                        0, format, GL_UNSIGNED_BYTE, image);
                glGenerateMipmap(GL_TEXTURE_2D);
                stbi_image_free(image);
            }
        }

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    @Override
    public void cleanup() {
        super.cleanup();

        texture.disableTexture();
    }
}
