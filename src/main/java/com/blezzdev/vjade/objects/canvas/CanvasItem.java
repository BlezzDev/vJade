package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.VJadeObject;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vector2;

public class CanvasItem<T extends CanvasItem<T>> extends VJadeObject<T> {
    private Color modulate = new Color(1, 1, 1, 1);
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2(1, 1);
    private Vector2 pivot = new Vector2(0.5f, 0.5f);
    protected int behavior = SizeBehavior.RELATIVE;

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
    public T setSizeBehavior(int behavior) {
        this.behavior = behavior;
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
}
