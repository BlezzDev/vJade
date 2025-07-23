package com.blezzdev.vjade.util.textures;

import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.ColorRGB;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

public class VJadeTextureShapeLoader {
    public static class RectangleShapeRenderer {
        public ByteBuffer fillRect(Vector2 size, ColorRGB colorRGB) {
            ByteBuffer texture = BufferUtils.createByteBuffer((int) (size.x * size.y * 4));
            for (int i = 0; i < size.x * size.y; i++) {
                texture.put((byte) colorRGB.getR());          // R
                texture.put((byte) colorRGB.getG());          // G
                texture.put((byte) colorRGB.getB());          // B
                texture.put((byte) (colorRGB.getA() * 255));  // A
            }
            texture.flip();
            return texture;
        }
    }
}
