package com.blezzdev.vjade.core.engine;

import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.tools.VJade;

public class Engine extends Window<Engine> {
    private final Logger logger = new Logger();

    public Engine() {
        VJade.setContext(this);
    }

    public Logger getLogger() {
        return logger;
    }

    public void launch() { run(); }
}
