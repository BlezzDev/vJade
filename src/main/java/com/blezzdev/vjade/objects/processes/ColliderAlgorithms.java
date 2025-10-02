package com.blezzdev.vjade.objects.processes;

import com.blezzdev.vjade.objects.collision.Collider;

public interface ColliderAlgorithms {
    default boolean rectangleRectangle(Collider selfCollider, Collider collider) {
        return false;
    }
}
