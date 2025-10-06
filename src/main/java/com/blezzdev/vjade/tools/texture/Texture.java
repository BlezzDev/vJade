package com.blezzdev.vjade.tools.texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    private final TextureRenderer renderer;
    private String resourcePath;
    private int width, height;

    public Texture(String resourcePath) {
        renderer = new TextureRenderer(this);
        setResourcePath(resourcePath);
    }

    private void putDimensions() {
        File imageFile = new File(getResourcePath());

        try {
            BufferedImage image = ImageIO.read(imageFile);

            if (image != null) {
                width = image.getWidth();
                height = image.getHeight();
            } else {
                System.out.println("Could not read image file or file is not a valid image.");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        putDimensions();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public TextureRenderer getRenderer() {
        return renderer;
    }
}
