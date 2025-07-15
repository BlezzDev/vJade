package com.blezzdev.vjade.util.textures;

import com.blezzdev.vjade.util.VJadeVector2;
import com.blezzdev.vjade.util.color.VJadeColorRGB;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

public class VJadeTextureShapeLoader {
    public static class RectangleShapeRenderer {
        public ByteBuffer fillRect(VJadeVector2 size, VJadeColorRGB colorRGB) {
            ByteBuffer redImage = BufferUtils.createByteBuffer((int) (size.x * size.y * 4));
            for (int i = 0; i < size.x * size.y; i++) {
                redImage.put((byte) colorRGB.getR());          // R
                redImage.put((byte) colorRGB.getG());          // G
                redImage.put((byte) colorRGB.getB());          // B
                redImage.put((byte) (colorRGB.getA() * 255));  // A
            }
            redImage.flip();
            return redImage;
        }
    }
}
