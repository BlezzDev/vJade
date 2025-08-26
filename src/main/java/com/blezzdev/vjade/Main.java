package com.blezzdev.vjade;

import com.blezzdev.vjade.core.engine.Engine;
import com.blezzdev.vjade.tools.color.ColorRGB;

public class Main {
    public static void main(String[] args) {
        Engine game = new Engine();

        game.setTitle("Balatro");
        game.setSize(1124, 680);
        game.setBackgroundColor(ColorRGB.BLACK);

        game.addScene(new DebugScene());

        game.runEngine("DebugScene");
    }
}