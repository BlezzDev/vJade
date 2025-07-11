package com.blezzdev.vjade;

import com.blezzdev.vjade.nodes.Sprite;
import com.blezzdev.vjade.util.TextureFilter;
import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.win.VJadeWindow;

public class Main {
    public static void main(String[] args) {
        VJadeWindow window = new VJadeWindow(1124, 680, "AppProject");
        window.show();

        window.setVsync();

        Sprite sprite = new Sprite(0, 0);
        sprite.setFilter(TextureFilter.NEAREST);
        sprite.setScale(new Vector2(2, 2));

        window.render(() -> {
            sprite.render();
        });

        window.destroy(() -> {
            sprite.destroy();
        });

        window.run();
    }
}