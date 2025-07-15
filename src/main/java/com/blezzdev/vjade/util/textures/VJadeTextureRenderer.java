package com.blezzdev.vjade.util.textures;

import com.blezzdev.vjade.util.VJadeVector2;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glDisable;

public class VJadeTextureRenderer {
    public static void render(int textureId, VJadeVector2 position, VJadeVector2 size) {

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex2f(position.x, position.y);
        glTexCoord2f(1, 0); glVertex2f(position.x + size.x, position.y);
        glTexCoord2f(1, 1); glVertex2f(position.x + size.x, position.y + size.y);
        glTexCoord2f(0, 1); glVertex2f(position.x, position.y + size.y);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    public static void destroy(int texture) { glDeleteTextures(texture); }
    public static void destroy(IntBuffer texture) { glDeleteTextures(texture); }
}
