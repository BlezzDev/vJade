package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.collision.RectangleCollider2D;

interface ColliderAlgorithms {
    default boolean rectangleRectangle2D(RectangleCollider2D selfCollider, RectangleCollider2D collider) {
        return false;
    }
}
