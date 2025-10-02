package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.core.engine.Logger;
import com.blezzdev.vjade.core.input.InputManager;
import com.blezzdev.vjade.core.manager.TimerManager;
import com.blezzdev.vjade.core.window.Monitor;
import com.blezzdev.vjade.core.window.WindowLogic;
import com.blezzdev.vjade.tools.VJade;

public class Screen extends VJObject {
    public Logger getLogger() { return VJade.getContext().getLogger(); }
    public InputManager getInput() { return VJade.getContext().getInput(); }
    public Monitor getMonitor() { return VJade.getContext().getMonitor(); }
    public WindowLogic getLogic() { return VJade.getContext().getLogic(); }

    public TimerManager getTimer() { return VJade.getContext().getLogic().getTimerManager(); }
}
