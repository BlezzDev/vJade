package com.blezzdev.vjade.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    private final String resourcePath;
    private float width, height;
    public Texture(String resourcePath) {
        this.resourcePath = resourcePath;
        putDimensions();
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
