package com.blezzdev.vjade.util;

import com.blezzdev.vjade.gui.VJadeUIScreen;

import java.util.HashMap;
import java.util.Map;

public class VJadeUIScreenProvider {
    private static final Map<Integer, VJadeUIScreen> REGISTERED_SCREENS = new HashMap<>();
    private static int idProvider = -1;

    public static int register(VJadeUIScreen obj) {
        idProvider++;

        REGISTERED_SCREENS.put(idProvider, obj);
        return idProvider;
    }

    public static void unregister(int id) {
        REGISTERED_SCREENS.remove(id);
    }

    public static Map<Integer, VJadeUIScreen> getRegisteredColliders() {
        return REGISTERED_SCREENS;
    }
}