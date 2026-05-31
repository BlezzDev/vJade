package com.blezzdev.vjade;

import com.blezzdev.vjade.core.gameloop.GameApplicationLoop;
import com.blezzdev.vjade.core.manager.ManagerRegistry;
import com.blezzdev.vjade.core.window.Window;
import com.blezzdev.vjade.core.window.WindowFactory;

import java.util.logging.Logger;

/**
 * Represents the main entry point and runtime container of a game application.
 *
 * <p>The {@code GameApplication} class owns the main application components,
 * including the {@link Window}, the {@link GameApplicationLoop}, the
 * {@link ManagerRegistry}, and an optional {@link Logger}.</p>
 *
 * <p>This class provides several constructors for creating an application with
 * either a custom window or a generated window using a name, width, and height.
 * It also exposes methods to launch and request the shutdown of the application
 * loop.</p>
 *
 * @since   1.0.0
 * @version 1.0.0
 * @author  Jesus David Alfaro Marenco
 */
public class GameApplication {

    /**
     * The window used by this application.
     */
    private final Window window;

    /**
     * The game loop responsible for controlling the application lifecycle.
     */
    private final GameApplicationLoop gameLoop;

    /**
     * The registry that stores and manages application managers.
     */
    private final ManagerRegistry managers = new ManagerRegistry();

    /**
     * Optional logger used by the application.
     */
    private Logger logger;

    /**
     * Creates a new game application using a custom window.
     *
     * <p>This constructor allows the application to use an already configured
     * {@link Window} instance.</p>
     *
     * @param window the custom window used by this application
     *
     * @see #GameApplication(String)
     * @see #GameApplication(String, int, int)
     */
    public GameApplication(Window window) {
        this.window = window;
        this.gameLoop = new GameApplicationLoop(this);
    }

    /**
     * Creates a new game application with the specified window name.
     *
     * <p>The window is created with a default size of {@code 800x600} pixels.</p>
     *
     * @param name the name or title of the application window
     *
     * @see #GameApplication(Window)
     * @see #GameApplication(String, int, int)
     */
    public GameApplication(String name) {
        this.window = WindowFactory.build(name, 800, 600);
        this.gameLoop = new GameApplicationLoop(this);
    }

    /**
     * Creates a new game application with a custom window name and size.
     *
     * @param name the name or title of the application window
     * @param width the initial width of the application window
     * @param height the initial height of the application window
     *
     * @see #GameApplication(Window)
     * @see #GameApplication(String)
     */
    public GameApplication(String name, int width, int height) {
        this.window = WindowFactory.build(name, width, height);
        this.gameLoop = new GameApplicationLoop(this);
    }

    /**
     * Starts the application lifecycle.
     *
     * <p>This method executes the complete game loop sequence by calling the
     * pre-loop, main-loop, and post-loop phases.</p>
     *
     * @see #shutdown()
     */
    public void launch() {
        gameLoop.preLoop();
        gameLoop.inLoop();
        gameLoop.postLoop();
    }

    /**
     * Requests the application loop to stop.
     *
     * <p>This method does not immediately destroy the application. Instead, it
     * requests the current game loop to finish safely.</p>
     *
     * @see #launch()
     */
    public void shutdown() {
        gameLoop.breakInLoop();
    }

    /**
     * Registers a logger for this application.
     *
     * <p>The registered logger can be shared across the application environment
     * to report messages, warnings, errors, or debugging information.</p>
     *
     * @param logger the logger assigned to this application
     * @return this application instance for chained method calls
     */
    public GameApplication addLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    /**
     * Returns the window used by this application.
     *
     * @return the application window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Returns the game loop used by this application.
     *
     * @return the application game loop
     */
    public GameApplicationLoop getGameLoop() {
        return gameLoop;
    }

    /**
     * Returns the manager registry used by this application.
     *
     * @return the application manager registry
     */
    public ManagerRegistry getManagers() {
        return managers;
    }

    /**
     * Returns the logger registered in this application.
     *
     * <p>This method may return {@code null} if no logger has been registered
     * through {@link #addLogger(Logger)}.</p>
     *
     * @return the registered logger, or {@code null} if no logger was added
     */
    public Logger getLogger() {
        return logger;
    }
}