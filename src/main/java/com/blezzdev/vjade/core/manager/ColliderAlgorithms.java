package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.collision.RectangleCollider;

interface ColliderAlgorithms {
    default boolean rectangleRectangle2D(RectangleCollider selfCollider, RectangleCollider collider) {
        return false;
    }
}
