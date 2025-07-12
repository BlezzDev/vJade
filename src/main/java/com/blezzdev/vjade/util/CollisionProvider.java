package com.blezzdev.vjade.util;

import com.blezzdev.vjade.nodes.collision.CollisionShape;

import java.util.HashMap;
import java.util.Map;

public class CollisionProvider {
    private static Map<Integer, CollisionShape> REGISTERED_COLLIDERS = new HashMap<>();
    private static int idProvider = -1;

    public static int register(CollisionShape obj) {
        idProvider++;

        REGISTERED_COLLIDERS.put(idProvider, obj);
        return idProvider;
    }

    public static void unregister(int id) {
        REGISTERED_COLLIDERS.remove(id);
    }

    public static Map<Integer, CollisionShape> getRegisteredColliders() {
        return REGISTERED_COLLIDERS;
    }
}