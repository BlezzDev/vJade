package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.engine.Logger;
import com.blezzdev.vjade.core.manager.CollisionManager;
import com.blezzdev.vjade.core.manager.TimerManager;
import com.blezzdev.vjade.tools.VJade;

/// The Engine class creates the window with the
/// settings that have been determined in the
/// chained methods.
///
/// - Position: manages the position of the window.
/// - Size: manages the size of the window.
/// - Title: manages the title displayed at the top of the window.
/// - BackgroundColor: manages the background color displayed in the window.
/// - Resizable: handles whether the window can be resized manually.
/// - Decorations: handles whether the window will contain borders by operating system default.
/// - Visible: handles whether the window is visible to the user.
/// - Vsync: handles whether the window has VSync active.
///
/// It also has methods to lighten the window configuration or offer control, such as:
///
/// - centered() centralize the position of a window according to the main monitor.
/// - fullscreen() make a window scaled to the entire main monitor.
/// - minimized() make a minimized window.
/// - maximized() make a maximized window.

public class Engine extends Window<Engine> {
    private final Logger logger = new Logger();

    public Logger getLogger() {
        return logger;
    }

    public TimerManager getTimerManager() { return windowLogic.getTimerManager(); }
    public CollisionManager getCollisionManager() { return windowLogic.getCollisionManager(); }
    public int getShaderProgram() { return windowLogic.getShaderProgram(); }

    public void launch() { VJade.setContext(this); run(); }
}
