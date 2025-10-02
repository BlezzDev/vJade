package com.blezzdev.vjade.tools;

import com.blezzdev.vjade.core.engine.Engine;

public class VJade {
    private static Engine context;

    public static void setContext(Engine context) {
        VJade.context = context;
    }

    public static Engine getContext() {
        return VJade.context;
    }

    public static boolean existContext() {
        return context != null;
    }
}