package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.GameApplication;

/**
 * Represents a base class for application managers.
 *
 * <p>A {@code Manager} is responsible for controlling a specific part of the
 * application logic, such as rendering, input, audio, assets, screens, or other
 * systems required by the game.</p>
 *
 * <p>Each manager receives a reference to the {@link GameApplication}, allowing
 * it to access shared application resources such as the window, game loop,
 * registry, or logger.</p>
 *
 * <p>Subclasses must define their own lifecycle behavior by implementing
 * {@link #init()}, {@link #update()}, and {@link #end()}.</p>
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */
public abstract class Manager {

    /**
     * The application instance associated with this manager.
     *
     * <p>This reference allows subclasses to access shared application
     * components and services.</p>
     */
    protected final GameApplication application;

    /**
     * Creates a new manager associated with the given application.
     *
     * @param application the application instance controlled or used by this manager
     */
    public Manager(GameApplication application) {
        this.application = application;
    }

    /**
     * Initializes this manager.
     *
     * <p>This method is intended to prepare resources, configure initial state,
     * or register elements required before the manager starts updating.</p>
     */
    protected abstract void init();

    /**
     * Updates this manager during the application loop.
     *
     * <p>This method is intended to contain the recurring logic executed while
     * the application is running.</p>
     */
    protected abstract void update();

    /**
     * Updates this manager during the application loop.
     *
     * <p>This method is intended to contain the recurring logic executed while
     * the application is running (after {@link #update()} method).</p>
     */
    protected abstract void postUpdate();

    /**
     * Finalizes this manager.
     *
     * <p>This method is intended to release resources, save state, unregister
     * elements, or perform cleanup before the application closes.</p>
     */
    protected abstract void end();
}