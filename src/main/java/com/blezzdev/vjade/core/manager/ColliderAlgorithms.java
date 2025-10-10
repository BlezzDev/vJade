package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.collision.Collider;

interface ColliderAlgorithms {
    default boolean rectangleRectangle(Collider selfCollider, Collider collider) {
        return false;
    }
}
