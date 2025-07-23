package com.blezzdev.vjade;

import com.blezzdev.vjade.nodes.Sprite;

import com.blezzdev.vjade.scenes.Scene;
import com.blezzdev.vjade.util.textures.TextureFilter;
import com.blezzdev.vjade.win.Window;

public class DebugScene extends Scene {
    public DebugScene(Window window, String name) {
        super(window, name);
    }

    Sprite s;

    @Override
    public void start() {
        super.start();
        s = new Sprite("assets/null.png");
        s.setScale(3, 3);
        s.setFilter(TextureFilter.NEAREST);
    }

    @Override
    public void render() {
        super.render();
        s.render();
    }

    @Override
    public void update() {
        super.update();

        s.moveX(1);
    }

    @Override
    public void finish() {
        super.finish();
        s.destroy();
    }
}