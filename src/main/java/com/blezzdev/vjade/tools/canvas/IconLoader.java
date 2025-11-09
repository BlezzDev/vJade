package com.blezzdev.vjade.tools.canvas;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwSetWindowIcon;

class IconLoader {
    private String resourcePath;
    private GLFWImage.Buffer iconBuffer;
    private ByteBuffer imageData;

    public IconLoader(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public void load() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            STBImage.stbi_set_flip_vertically_on_load(false);
            imageData = STBImage.stbi_load(resourcePath, w, h, comp, 4);

            if (imageData == null) {
                System.err.println("Error loading icon: " + STBImage.stbi_failure_reason());
                return;
            }

            iconBuffer = GLFWImage.malloc(1);
            iconBuffer.position(0);
            iconBuffer.width(w.get(0));
            iconBuffer.height(h.get(0));
            iconBuffer.pixels(imageData);
        }
    }

    public void applyToWindow(long windowHandle) {
        if (iconBuffer != null) {
            glfwSetWindowIcon(windowHandle, iconBuffer);
        } else {
            System.err.println("The icon is not loaded before applying.");
        }
    }

    public void destroy() {
        if (imageData != null)
            STBImage.stbi_image_free(imageData);
        if (iconBuffer != null)
            iconBuffer.free();
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
