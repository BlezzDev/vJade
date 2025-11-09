package com.blezzdev.vjade.tools.data.geometry.specialized;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public class Target {
    private Vec2 point;
    private float smooth;
    private float zoom;

    public Target() {
        this(new Vec2());
    }
    public Target(Vec2 point) {
        this(point, 1, 1);
    }
    public Target(Vec2 point, float smooth, float zoom) {
        setPoint(point).setSmooth(smooth).setZoom(zoom);
    }

    public Target setPoint(Vec2 point) {
        this.point = point;
        return this;
    }

    public Target setSmooth(float smooth) {
        this.smooth = Math.max(0, Math.min(1, smooth));
        return this;
    }

    public void setZoom(float zoom) {
        this.zoom = Math.max(0, zoom);
    }

    public Vec2 getPoint() {
        return point;
    }

    public float getSmooth() {
        return smooth;
    }

    public float getZoom() {
        return zoom;
    }
}
