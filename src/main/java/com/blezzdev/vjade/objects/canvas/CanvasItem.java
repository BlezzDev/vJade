package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.VJObject2D;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vector2;

public class CanvasItem<T extends CanvasItem<T>> extends VJObject2D<T> {
    private Color modulate = new Color(1, 1, 1, 1);
    private Vector2 pivot = new Vector2(0.5f, 0.5f);
    private float zIndex = 0;

    protected Behavior behavior = Behavior.RELATIVE;

    public enum Behavior {
        RELATIVE, FIXED
    }

    @SuppressWarnings("unchecked")
    public T setModulate(Color modulate) {
        this.modulate = modulate;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setSizeBehavior(Behavior behavior) {
        this.behavior = behavior;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setPivot(float x, float y) { setPivot(new Vector2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setPivot(Vector2 pivot) {
        this.pivot = pivot;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setzIndex(float zIndex) {
        this.zIndex = zIndex;
        return (T) this;
    }

    public Color getModulate() {
        return modulate;
    }

    public float getzIndex() { return zIndex; }

    public Vector2 getPivot() {
        return pivot;
    }

    public Behavior getSizeBehavior() { return behavior; }
}