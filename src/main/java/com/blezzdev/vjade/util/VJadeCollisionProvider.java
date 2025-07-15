package com.blezzdev.vjade.util;

import com.blezzdev.vjade.nodes.collision.VJadeCollisionShape;

import java.util.HashMap;
import java.util.Map;

public class VJadeCollisionProvider {
    private static Map<Integer, VJadeCollisionShape> REGISTERED_COLLIDERS = new HashMap<>();
    private static int idProvider = -1;

    public static int register(VJadeCollisionShape obj) {
        idProvider++;

        REGISTERED_COLLIDERS.put(idProvider, obj);
        return idProvider;
    }

    public static void unregister(int id) {
        REGISTERED_COLLIDERS.remove(id);
    }

    public static Map<Integer, VJadeCollisionShape> getRegisteredColliders() {
        return REGISTERED_COLLIDERS;
    }
}