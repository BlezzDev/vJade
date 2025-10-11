package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.core.manager.CollisionManager;
import com.blezzdev.vjade.objects.build.vjobj.VJObject;
import com.blezzdev.vjade.util.types.Shapes;

public class Collider extends VJObject {
    protected final CollisionManager collisionManager;
    protected final Shapes shape;
    private final int id;

    protected boolean colliding;

    public Collider(Shapes shape) {
        this.shape = shape;
        this.collisionManager = getContext().getCollisionManager();
        id = collisionManager.link(this);
    }

    private boolean compare(Collider collider, Shapes shape) {
        return compare(collider, shape, shape);
    }
    private boolean compare(Collider collider, Shapes selfShape, Shapes shape) {
        return getShape() == selfShape && collider.getShape() == shape;
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        for (Collider collider : collisionManager.getColliderList()) {
            if (collider != this) {
                if (compare(collider, Shapes.RECTANGLE)) {
                    colliding = collisionManager.rectangleRectangle2D((RectangleCollider2D) this, (RectangleCollider2D) collider);
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();

        collisionManager.unlink(id);
    }

    public boolean isColliding() {
        return colliding;
    }

    public int getId() {
        return id;
    }
    public Shapes getShape() { return shape; }
}
