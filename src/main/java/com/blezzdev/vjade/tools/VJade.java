package com.blezzdev.vjade.tools;

import com.blezzdev.vjade.core.window.Engine;

/// The VJade class manages the static values of a program.
///
/// The context keeps track of the window that is currently running.

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