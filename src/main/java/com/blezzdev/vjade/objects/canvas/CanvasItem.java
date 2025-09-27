package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.VJadeObject;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vector2;

public class CanvasItem<T extends CanvasItem<T>> extends VJadeObject<T> {
    private Color modulate = new Color(1, 1, 1, 1);
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2(1, 1);
    private Vector2 pivot = new Vector2(0.5f, 0.5f);
    private float zIndex = 0;
    protected SizeBehavior behavior = SizeBehavior.RELATIVE;

    public enum SizeBehavior {
        RELATIVE, FIXED
    }

    @SuppressWarnings("unchecked")
    public T setPosition(Vector2 position) {
        this.position = position;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setSize(Vector2 size) {
        this.size = size;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setPivot(Vector2 pivot) {
        this.pivot = pivot;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setModulate(Color modulate) {
        this.modulate = modulate;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setSizeBehavior(SizeBehavior behavior) {
        this.behavior = behavior;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setzIndex(float zIndex) {
        this.zIndex = zIndex;
        return (T) this;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }

    public Color getModulate() {
        return modulate;
    }

    public Vector2 getPivot() {
        return pivot;
    }

    public float getzIndex() { return zIndex; }

    public SizeBehavior getSizeBehavior() { return behavior; }
}