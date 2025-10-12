package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.tools.data.geometry.Vector2;

public interface PointItem<T> {
    class Point {
        private Vector2 position = new Vector2();

        public void setPosition(Vector2 position) {
            this.position = position;
        }

        public Vector2 getPosition() { return position; }
    }

    Point point = new Point();

    @SuppressWarnings("unchecked")
    default T setPosition(float x, float y) { setPosition(new Vector2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    default T setPosition(Vector2 position) {
        point.setPosition(position);
        return (T) this;
    }

    default Vector2 getPosition() {
        return point.getPosition();
    }
}
