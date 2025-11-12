package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.core.manager.UserInterfaceManager;
import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.core.manager.collider.CollisionManager;
import com.blezzdev.vjade.core.manager.input.InputManager;
import com.blezzdev.vjade.core.manager.timer.TimerManager;
import com.blezzdev.vjade.tools.canvas.Shader;
import com.blezzdev.vjade.tools.VJade;

/**
 * The {@code Engine} class is responsible for creating and managing the <b>main window</b>
 * using the configuration provided through chained method calls.
 *
 * <p>It allows setting various window parameters:</p>
 *
 * <ul>
 *   <li><b>Position</b> manages the position of the window.</li>
 *   <li><b>Size</b> manages the dimensions of the window.</li>
 *   <li><b>Title</b> sets the title displayed in the window’s title bar.</li>
 *   <li><b>BackgroundColor</b> defines the background color displayed in the window.</li>
 *   <li><b>Resizable</b> determines whether the window can be manually resized.</li>
 *   <li><b>Decorations</b> determines whether the window uses the operating system’s default borders.</li>
 *   <li><b>Visible</b> controls whether the window is visible to the user.</li>
 *   <li><b>VSync</b> enables or disables vertical synchronization.</li>
 * </ul>
 *
 * <p>It also provides utility methods for managing window states:</p>
 *
 * <ul>
 *   <li>{@link #centered()} — centers the window on the primary monitor.</li>
 *   <li>{@link #fullscreen()} — scales the window to fill the primary monitor.</li>
 *   <li>{@link #minimized()} — minimizes the window.</li>
 *   <li>{@link #maximized()} — maximizes the window.</li>
 * </ul>
 *
 * @author Alfaro
 * @version 1
 * @since vJade 1
 */

public class Engine extends Window<Engine> {
    public Shader getShader() { return windowLogic.getShader(); }

    public UserInterfaceManager getUserInterface() { return getManagers().getUserInterface(); }
    public CollisionManager getCollision() { return getManagers().getCollision(); }
    public ScreenManager getScreen() { return getManagers().getScreen(); }
    public CanvasManager getCanvas() { return getManagers().getCanvas(); }
    public TimerManager getTimer() { return getManagers().getTimer(); }
    public InputManager getInput() { return getManagers().getInput(); }

    /**
     * {@link #launch()} is a method that activates the program's life cycle.
     *
     * <p>First, the {@link VJade} class is established as the current context,
     * and then runs the program's life cycle.</p>
     *
     * */

    public Engine launch() { VJade.setContext(this); run(); return this; }

    @Override
    public String toString() {
        return getTitle();
    }
}
