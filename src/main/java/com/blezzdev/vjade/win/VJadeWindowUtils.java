package com.blezzdev.vjade.win;

import com.blezzdev.vjade.scenes.Scene2D;
import com.blezzdev.vjade.scenes.VJadeSceneRegistries;
import com.blezzdev.vjade.util.color.ColorHSV;

import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;

/**
 * It is not recommended to use
 * this class since it works
 * for internal functionalities of the library.
 */

/*
*  VJadeWindowUtils is a private class in raw code
*  to manage advanced methods of VJadeWindowLogic.
*/

public class VJadeWindowUtils {
    public static void beforeLoop(String currentScene, Scene2D scene) {
        if (currentScene != null) {scene.start();}
        else { System.err.println("VJadeWindow requires a initial scene to work."); }
    }

    public static void setBackgroundColor(ColorHSV backgroundColor) {
        glClearColor(backgroundColor.getR(), backgroundColor.getG(), backgroundColor.getB(), backgroundColor.getA());
        glClear(GL_COLOR_BUFFER_BIT);
    }

    public static String updateScene(String currentScene, String last) {
        if (!Objects.equals(last, currentScene)) {
            Scene2D oldScene = VJadeSceneRegistries.getScene(last);
            Scene2D newScene = VJadeSceneRegistries.getScene(currentScene);

            if (oldScene != null) oldScene.finish();
            if (newScene != null) newScene.start();

            last = currentScene;
        }
        return last;
    }
}
