package com.blezzdev.vjade;

import com.blezzdev.vjade.core.engine.Engine;
import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.tools.data.color.ColorRGB;

public class DebugMain {
    public static void main(String[] args) {
        Engine game = new Engine()
                .setBackgroundColor(new ColorRGB(0, 0, 0))
                .setSize(1124, 680)
                .setState(Window.State.CENTERED)
                .setTitle("vJade")

                .setResizable(true)
                .setDecorations(true)
                .setVsync(true)

                .addScreen(new DebugScreenOne(), "debug1")
                .addScreen(new DebugScreenTwo())

                .setMainScreen("debug1");

        game.launch();
    }
}