package com.blezzdev.vjade;

import com.blezzdev.vjade.nodes.Sprite2D;

import com.blezzdev.vjade.scenes.Scene2D;
import com.blezzdev.vjade.util.textures.TextureFilter;
import com.blezzdev.vjade.win.Window;

public class DebugScene extends Scene2D {
    public DebugScene(Window window, String name) {
        super(window, name);
    }

    Sprite2D s;

    @Override
    public void start() {
        super.start();
        s = new Sprite2D("assets/null.png");
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