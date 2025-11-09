package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.item.VJRect;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.specialized.Pivot;
import com.blezzdev.vjade.tools.data.geometry.Rect2;
import com.blezzdev.vjade.util.types.Behavior;

public class CanvasItem<T extends CanvasItem<T>> extends VJRect<T> {
    private Rect2 frame = new Rect2();
    private Color modulate = new Color(1, 1, 1, 1);
    private Pivot pivot = new Pivot(0.5f, 0.5f);
    private float rotation = 0;
    private float zIndex = 0;

    protected Behavior behavior = Behavior.RELATIVE;

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
    public T setPivot(float x, float y) { setPivot(new Pivot(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setPivot(Pivot pivot) {
        this.pivot = pivot;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setRotation(float rotation_degrees) {
        this.rotation = rotation_degrees;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setzIndex(float zIndex) {
        this.zIndex = zIndex;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setFrame(float x, float y, float width, float height) { setFrame(new Rect2(x, y, width, height)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setFrame(Rect2 frame) {
        this.frame = frame;
        return (T) this;
    }

    public Color getModulate() {
        return modulate;
    }

    public float getzIndex() { return zIndex; }

    public Pivot getPivot() { return pivot; }

    public float getRotation() { return rotation; }

    public Behavior getSizeBehavior() { return behavior; }

    public Rect2 getFrame() {
        return frame;
    }
}