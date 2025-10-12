package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.tools.render.Texture;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

class Icon {
    private final long glWindow;
    private Texture texture;

    public Icon(long window) {
        this.glWindow = window;
    }

    public void loadIcon(Texture texture) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer icon = STBImage.stbi_load(texture.getResourcePath(), w, h, comp, 4);
            if (icon == null) {
                throw new RuntimeException("The icon could not be loaded: " + STBImage.stbi_failure_reason());
            }

            GLFWImage.Buffer iconBuffer = GLFWImage.malloc(1);
            iconBuffer.position(0);
            iconBuffer.width(w.get(0));
            iconBuffer.height(h.get(0));
            iconBuffer.pixels(icon);

            GLFW.glfwSetWindowIcon(glWindow, iconBuffer);

            STBImage.stbi_image_free(icon);

            this.texture = texture;
        }
    }

    public Texture getTexture() {
        return texture;
    }
}
