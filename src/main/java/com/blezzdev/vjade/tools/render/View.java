package com.blezzdev.vjade.tools.render;

import com.blezzdev.vjade.tools.data.geometry.Vec2;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class View {
    private Vec2 position;
    private float rotation = 0.0f;
    private float zoom = 1.0f;
    private float viewportWidth;
    private float viewportHeight;

    private Vector2f worldMin = new Vector2f(Float.NEGATIVE_INFINITY);
    private Vector2f worldMax = new Vector2f(Float.POSITIVE_INFINITY);

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
                .identity()
                .translate(getPosition().x, getPosition().y, 0)
                .rotate((float)Math.toRadians(-rotation), 0, 0, 1)
                .scale(1f / zoom, 1f / zoom, 1);
    }


    public View setPosition(Vec2 position) { this.position = position; return this; }
    public View setRotation(float rotation) { this.rotation = rotation; return this; }
    public View setZoom(float zoom) { this.zoom = Math.max(0.1f, zoom); return this; }
    public View setWorldBounds(float minX, float minY, float maxX, float maxY) {
        worldMin.set(minX, minY);
        worldMax.set(maxX, maxY);
        return this;
    }

    public Vec2 getPosition() {
        return position;
    }
}
