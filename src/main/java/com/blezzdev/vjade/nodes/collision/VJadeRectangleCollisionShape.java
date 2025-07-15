package com.blezzdev.vjade.nodes.collision;

import com.blezzdev.vjade.nodes.VJadeSprite;
import com.blezzdev.vjade.util.VJadeCollisionProvider;
import com.blezzdev.vjade.util.VJadeVector2;
import com.blezzdev.vjade.util.color.VJadeColorHSV;

public class VJadeRectangleCollisionShape extends VJadeCollisionShape {
    public VJadeRectangleCollisionShape(VJadeVector2 position, VJadeVector2 size) {
        super(position, size);
    }

    private boolean rendering = false;
    private VJadeSprite sprite = new VJadeSprite(new VJadeColorHSV(0, 0.2f, 0.85f, 0.5f));

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

        for (VJadeCollisionShape singleObj : VJadeCollisionProvider.getRegisteredColliders().values()) {
            if (singleObj != null && singleObj != this) {
                VJadeVector2 otherPos = singleObj.getPosition();
                VJadeVector2 otherSize = singleObj.getSize();

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
