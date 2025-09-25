package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.core.input.InputManager;

public class Screen extends VJadeObject<Screen> {
    public InputManager getInput() {
        return getWindow().getInput();
    }
}
