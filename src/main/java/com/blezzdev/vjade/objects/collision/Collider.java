package com.blezzdev.vjade.objects.collision;

import com.blezzdev.vjade.core.manager.collider.CollisionManager;
import com.blezzdev.vjade.objects.build.item.VJObject;
import com.blezzdev.vjade.util.types.Shapes;

public class Collider extends VJObject {
    protected final CollisionManager collisionManager;
    protected final Shapes shape;
    private final String id;

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

        for (Collider collider : collisionManager.getColliderList().values()) {
            if (collider != this) {

                // Collisions of the same shapes.

                if (compare(collider, Shapes.RECTANGLE)) {
                    colliding = collisionManager.rectangleRectangle(
                            (RectangleCollider) this, (RectangleCollider) collider);
                }

                if (compare(collider, Shapes.CIRCLE)) {
                    colliding = collisionManager.circleCircle(
                            (CircleCollider) this, (CircleCollider) collider);
                }


                // Collisions between different shapes.

                if (compare(collider, Shapes.CIRCLE, Shapes.RECTANGLE)) {
                    colliding = collisionManager.rectangleCircle(
                            (RectangleCollider) this, (CircleCollider) collider);
                } else if (compare(collider, Shapes.RECTANGLE, Shapes.CIRCLE)) {
                    colliding = collisionManager.rectangleCircle(
                            (RectangleCollider) collider, (CircleCollider) this);
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

    public String getId() {
        return id;
    }
    public Shapes getShape() { return shape; }
}
