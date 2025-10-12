package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.tools.data.geometry.Vector2;

public interface RectItem<T> extends PointItem<T> {
    class Rect {
        private Vector2 size = new Vector2(1, 1);

        public void setSize(Vector2 size) {
            this.size = size;
        }

        public Vector2 getSize() {
            return size;
        }
    }

    Rect rect = new Rect();

    @SuppressWarnings("unchecked")
    default T setSize(float x, float y) { setSize(new Vector2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    default T setSize(Vector2 position) {
        rect.setSize(position);
        return (T) this;
    }

    default Vector2 getSize() {
        return rect.getSize();
    }
}
