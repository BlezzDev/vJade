package com.blezzdev.vjade.core.manager.collider;

import com.blezzdev.vjade.objects.collision.CircleCollider;
import com.blezzdev.vjade.objects.collision.RectangleCollider;

interface ColliderAlgorithms {
    default boolean rectangleRectangle(RectangleCollider selfCollider, RectangleCollider collider) {
        return false;
    }
    default boolean rectangleCircle(RectangleCollider selfCollider, CircleCollider collider) {
        return false;
    }
    default boolean circleCircle(CircleCollider selfCollider, CircleCollider collider) {
        return false;
    }
}
