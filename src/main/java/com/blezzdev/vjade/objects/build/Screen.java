package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.core.input.InputManager;
import com.blezzdev.vjade.core.window.Monitor;
import com.blezzdev.vjade.core.window.WindowLogic;

public class Screen extends VJadeObject<Screen> {
    public InputManager getInput() {
        return getWindow().getInput();
    }
    public Monitor getMonitor() { return getWindow().getMonitor(); }
    public WindowLogic getLogic() { return getWindow().getLogic(); }
}
