package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.item.PointItem;
import com.blezzdev.vjade.objects.build.item.VJObject;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.geometry.Rect2;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.render.View;

public class Camera2D extends VJObject implements PointItem<Camera2D> {
    private float rotation = 0f;
    private float zoom = 1f;
    private Rect2 worldBounds = new Rect2(
            Float.NEGATIVE_INFINITY,
            Float.NEGATIVE_INFINITY,
            Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY
    );

    private final View view;

    private Vec2 target;
    private float followSmoothness = 0.1f;
    private boolean following = false;

    public Camera2D() {
        view = new View()
                .setWorldBounds(worldBounds.x, worldBounds.y, worldBounds.width, worldBounds.height)
                .setZoom(1)
                .setRotation(0)
                .setPosition(0, 0);

        VJade.setView(view);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (following && target != null) {
            Vec2 currentPos = getPosition();

            float factor = 1f - (float)Math.pow(1f - followSmoothness, deltaTime * 60f);

            float newX = currentPos.x + (target.x - currentPos.x) * factor;
            float newY = currentPos.y + (target.y - currentPos.y) * factor;

            setPosition(newX, newY);
            view.setPosition(getPosition());
        }

        view.setPosition(getPosition());
        view.setRotation(rotation);
        view.setZoom(zoom);
    }

    public Camera2D stopFollowing() {
        this.following = false;
        this.target = null;
        return this;
    }

    public Camera2D follow(Vec2 target, float smoothness) {
        this.target = target;
        this.followSmoothness = smoothness;
        this.following = true;
        return this;
    }

    @Override
    public Camera2D setPosition(float x, float y) {
        PointItem.super.setPosition(x, y);
        view.setPosition(getPosition());
        return this;
    }

    public Camera2D setRotation(float rotation) {
        this.rotation = rotation;
        view.setRotation(rotation);
        return this;
    }

    public Camera2D setZoom(float zoom) {
        this.zoom = zoom;
        view.setZoom(zoom);
        return this;
    }

    public Camera2D setWorldBounds(Rect2 bounds) {
        this.worldBounds = bounds;
        view.setWorldBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        return this;
    }

    public Rect2 getWorldBounds() {
        return worldBounds;
    }

    public float getRotation() {
        return rotation;
    }

    public float getZoom() {
        return zoom;
    }
}
