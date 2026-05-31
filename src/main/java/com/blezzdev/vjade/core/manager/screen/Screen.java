package com.blezzdev.vjade.core.manager.screen;

import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.gameloop.GameApplicationLoop;
import com.blezzdev.vjade.core.manager.Manager;
import com.blezzdev.vjade.core.manager.ManagerRegistry;
import com.blezzdev.vjade.core.window.Window;

import java.util.logging.Logger;

/**
 * Represents a base class for application screens.
 *
 * <p>A {@code Screen} defines a specific visual or logical state of the
 * application, such as a main menu, loading screen, gameplay screen, settings
 * screen, or pause screen.</p>
 *
 * <p>Each screen receives a reference to the {@link GameApplication}, allowing
 * it to access shared application components such as the window, managers,
 * game loop, or logger.</p>
 *
 * <p>Subclasses must define their own lifecycle behavior by implementing
 * {@link #start()}, {@link #update()}, {@link #end()},
 * {@link #endScreen()}, and {@link #endProgram()}.</p>
 *
 * @since   1.0.0
 * @version 1.0.0
 * @author  Jesus David Alfaro Marenco
 */
public abstract class Screen {

    /**
     * The application instance associated with this screen.
     */
    private final GameApplication application;

    /**
     * Creates a new screen associated with the given application.
     *
     * @param application the application instance used by this screen
     */
    public Screen(GameApplication application) {
        this.application = application;
    }

    /**
     * Starts this screen.
     *
     * <p>This method is called when the screen becomes active. It should be used
     * to initialize screen resources, configure initial state, load elements,
     * or prepare objects required by the screen.</p>
     */
    protected abstract void start();

    /**
     * Updates this screen while it is active.
     *
     * <p>This method is called during the application update cycle and should
     * contain the recurring logic of the screen.</p>
     */
    protected abstract void update();

    /**
     * Ends this screen lifecycle.
     *
     * <p>This method should be used to release resources, clear temporary data,
     * or perform cleanup operations after the screen is no longer needed.</p>
     */
    protected abstract void end();

    /**
     * Handles the screen transition ending behavior.
     *
     * <p>This method is called when the active screen is replaced by another
     * screen. It should contain logic that only applies when leaving this
     * screen, but not necessarily when the whole application closes.</p>
     */
    protected abstract void endScreen();

    /**
     * Handles the program ending behavior for this screen.
     *
     * <p>This method is called when the application is shutting down while this
     * screen is active. It should contain logic related to final program
     * cleanup, saving state, or releasing global resources used by the screen.</p>
     */
    protected abstract void endProgram();

    /**
     * Returns the application instance associated with this screen.
     *
     * @return the application instance used by this screen
     */
    public GameApplication getApplication() {
        return application;
    }

    /**
     * Returns the window used by this application.
     *
     * @return the application window
     */
    public Window getWindow() {
        return application.getWindow();
    }

    /**
     * Returns the game loop used by this application.
     *
     * @return the application game loop
     */
    public GameApplicationLoop getGameLoop() {
        return application.getGameLoop();
    }

    /**
     * Returns the manager registry used by this application.
     *
     * @return the application manager registry
     */
    public ManagerRegistry getManagers() {
        return application.getManagers();
    }

    /**
     * Returns the logger registered in this application.
     *
     * @return the registered logger, or {@code null} if no logger was added
     */
    public Logger getLogger() {
        return application.getLogger();
    }

    /**
     * Returns the specified manager used by this application.
     *
     * @return the application manager registry
     */
    public <T extends Manager> T getManager(Class<T> manager) {
        return application.getManagers().get(manager);
    }
}