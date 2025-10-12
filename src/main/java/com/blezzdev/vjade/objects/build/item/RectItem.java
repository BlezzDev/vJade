package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public interface RectItem<T> extends PointItem<T> {
    class Rect {
        private Vec2 size = new Vec2(1, 1);

        public void setSize(Vec2 size) {
            this.size = size;
        }

        public Vec2 getSize() {
            return size;
        }
    }

    Rect rect = new Rect();

    @SuppressWarnings("unchecked")
    default T setSize(float x, float y) { setSize(new Vec2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    default T setSize(Vec2 position) {
        rect.setSize(position);
        return (T) this;
    }

    default Vec2 getSize() {
        return rect.getSize();
    }
}
