package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.core.manager.CollisionManager;
import com.blezzdev.vjade.objects.build.vjobj.VJObject2D;

public class Collider extends VJObject2D<Collider> {
    private final CollisionManager collisionManager;

    private final int id;
    private CollisionShape shape;

    protected boolean colliding;

    public enum CollisionShape {
        RECTANGLE
    }

    public Collider() {
        this.collisionManager = getContext().getCollisionManager();
        id = collisionManager.link(this);
    }

    private boolean compare(Collider collider, CollisionShape shape) {
        return compare(collider, shape, shape);
    }
    private boolean compare(Collider collider, CollisionShape selfShape, CollisionShape shape) {
        return getShape() == selfShape && collider.getShape() == shape;
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        for (Collider collider : collisionManager.getColliderList()) {
            if (collider != this) {
                if (compare(collider, CollisionShape.RECTANGLE)) {
                    colliding = collisionManager.rectangleRectangle(this, collider);
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();

        collisionManager.unlink(id);
    }

    public Collider setShape(CollisionShape shape) {
        this.shape = shape;
        return this;
    }

    public boolean isColliding() {
        return colliding;
    }

    public int getId() {
        return id;
    }
    public CollisionShape getShape() { return shape; }
}
