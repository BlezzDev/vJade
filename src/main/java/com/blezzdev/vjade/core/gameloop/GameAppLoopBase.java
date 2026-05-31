package com.blezzdev.vjade.core.gameloop;

import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.window.WindowProperties;
import org.lwjgl.opengl.GL11C;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL11C.GL_BLEND;
import static org.lwjgl.opengl.GL11C.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11C.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11C.glBlendFunc;
import static org.lwjgl.opengl.GL11C.glEnable;

class GameAppLoopBase {
    protected final GameApplication application;

    GameAppLoopBase(GameApplication application) { this.application = application; }

    void configureWindowBehaviour() {
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    void prepareClearColor() {
        WindowProperties properties = application.getWindow().getProperties();

        GL11C.glClearColor(properties.getBaseColor().getRed(),
                properties.getBaseColor().getGreen(),
                properties.getBaseColor().getBlue(),
                0f);
    }
}
