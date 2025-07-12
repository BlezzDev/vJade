package com.blezzdev.vjade;

import com.blezzdev.vjade.nodes.*;
import com.blezzdev.vjade.nodes.collision.RectangleCollisionShape;
import com.blezzdev.vjade.util.*;
import com.blezzdev.vjade.util.textures.TextureFilter;
import com.blezzdev.vjade.win.VJadeWindow;

public class Main {
    public static void main(String[] args) {
        VJadeWindow window = new VJadeWindow(1124, 680, "AppProject");
        window.setVsync();
        window.show();

        Sprite sprite = new Sprite("assets/null.png");
        sprite.setFilter(TextureFilter.NEAREST);
        sprite.setScale(new Vector2(2, 2));

        RectangleCollisionShape collide2 = new RectangleCollisionShape(new Vector2(200, 0), new Vector2(64, 64));
        RectangleCollisionShape collide = new RectangleCollisionShape(new Vector2(0, 200), new Vector2(64, 64));

        collide.setRenderingCollision(true);
        collide2.setRenderingCollision(true);

        window.render(() -> {
            sprite.render();
            collide.render();
            collide2.render();

            if (!collide.isColliding()) {
                collide.moveY(-1);
                collide.moveX(1);
            }
        });

        window.destroy(() -> {
            sprite.destroy();
        });

        window.run();
    }
}