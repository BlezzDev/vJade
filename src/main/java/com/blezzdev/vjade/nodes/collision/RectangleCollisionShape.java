package com.blezzdev.vjade.nodes.collision;

import com.blezzdev.vjade.nodes.Sprite;
import com.blezzdev.vjade.util.CollisionProvider;
import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.VJadeColorHSV;

public class RectangleCollisionShape extends CollisionShape {
    public RectangleCollisionShape(Vector2 position, Vector2 size) {
        super(position, size);
    }

    private boolean rendering = false;
    private Sprite sprite = new Sprite(new VJadeColorHSV(0, 0.2f, 0.85f, 0.5f));

    public void setRenderingCollision(boolean value) { rendering = value; }
    public boolean getRenderingCollision() { return rendering; }

    @Override
    public void render() {
        super.render();

        colliding = false;
        if (getRenderingCollision()) {
            sprite.render();
            sprite.setPosition(getPosition());
        }

        for (int i = 0; i < CollisionProvider.getRegisteredColliders().size(); i++) {
            CollisionShape singleObj = CollisionProvider.getRegisteredColliders().get(i);
            if (singleObj != null && singleObj != this) {
                Vector2 otherPos = singleObj.getPosition();
                Vector2 otherSize = singleObj.getSize();

                boolean collides = position.x < otherPos.x + otherSize.x / 2 &&
                        position.x + size.x / 2 > otherPos.x &&
                        position.y < otherPos.y + otherSize.y / 2 &&
                        position.y + size.y / 2 > otherPos.y;
                if (collides) {
                    colliding = true;
                }
            }
        }
    }
}
