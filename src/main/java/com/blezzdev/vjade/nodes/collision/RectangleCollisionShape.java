package com.blezzdev.vjade.nodes.collision;

import com.blezzdev.vjade.util.VJadeCollisionRegistries;
import com.blezzdev.vjade.util.Vector2;
import com.blezzdev.vjade.util.color.ColorRGB;
import com.blezzdev.vjade.util.textures.VJadeTextureRenderer;
import com.blezzdev.vjade.util.textures.VJadeTextureShapeLoader;
import com.blezzdev.vjade.util.textures.VJadeTextureShapeRenderer;

import java.nio.ByteBuffer;

public class RectangleCollisionShape extends VJadeCollisionShape {
    private ByteBuffer shape;
    private ColorRGB color;
    private int textureId;

    public RectangleCollisionShape(Vector2 position, Vector2 size, ColorRGB color) {
        super(position, size);

        this.color = color;

        VJadeTextureShapeRenderer renderer = new VJadeTextureShapeRenderer();
        this.shape = renderer.fillRect(size, color);
        this.textureId = renderer.textureId;
    }

    @Override
    public void render() {
        super.render();
        VJadeTextureRenderer.render(textureId, position, size);
    }

    @Override
    public void update() {
        super.update();

        this.setCollidingState(false);

        for (VJadeCollisionShape singleObj : VJadeCollisionRegistries.getRegisteredColliders().values()) {
            if (singleObj != null && singleObj != this) {
                Vector2 otherPos = singleObj.getPosition();
                Vector2 otherSize = singleObj.getSize();

                boolean collides = this.getPosition().x < otherPos.x + otherSize.x / 2 &&
                        this.getPosition().x + this.getSize().x / 2 > otherPos.x &&
                        this.getPosition().y < otherPos.y + otherSize.y / 2 &&
                        this.getPosition().y + this.getSize().y / 2 > otherPos.y;
                if (collides) {
                    if (this.getLayer() > 0 && this.getLayer() != singleObj.getLayer()) { return; }
                    this.setCollidingState(false);
                }
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        VJadeTextureRenderer.destroy(shape.asIntBuffer());
    }

    public ColorRGB getColor() { return color; }

    public void setColor(ColorRGB color) { this.color = color; };
}