package com.blezzdev.vjade.objects.build.item;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

public interface PointItem<T> {
    class Point {
        private Vec2 position = new Vec2();

        public void setPosition(Vec2 position) {
            this.position = position;
        }

        public Vec2 getPosition() { return position; }
    }

    Point point = new Point();

    @SuppressWarnings("unchecked")
    default T setPosition(float x, float y) { setPosition(new Vec2(x, y)); return (T) this; }
    @SuppressWarnings("unchecked")
    default T setPosition(Vec2 position) {
        point.setPosition(position);
        return (T) this;
    }

    default Vec2 getPosition() {
        return point.getPosition();
    }
}
