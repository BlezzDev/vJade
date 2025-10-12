package com.blezzdev.vjade.tools.render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    private String resourcePath;
    private int width, height;

    public Texture(String resourcePath) {
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
                System.err.println("Could not read image file or file is not a valid image.");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Texture setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        putDimensions();

        return this;
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
}
