package com.blezzdev.vjade.core.manager.screen;

import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.manager.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Manages the registration, activation, transition, update, and shutdown of
 * application screens.
 *
 * <p>The {@code ScreenManager} stores screen suppliers using string
 * identifiers. A screen can be selected as the current screen by its identifier,
 * and the manager handles the transition from the previous active screen to the
 * new one.</p>
 *
 * <p>This manager does not store screen instances permanently. Instead, it
 * stores {@link Supplier} objects that create screen instances when needed.</p>
 *
 * <p>During each update cycle, the manager checks whether the requested screen
 * is different from the active screen. If a transition is required, the current
 * active screen is ended, a new screen is created, and the new screen is
 * started.</p>
 *
 * @since   1.0.0
 * @version 1.0.0
 * @author  Jesus David Alfaro Marenco
 */
public class ScreenManager extends Manager {

    /**
     * Stores the registered screen suppliers by their identifiers.
     */
    private final Map<String, Supplier<? extends Screen>> screens = new HashMap<>();

    /**
     * The identifier of the screen requested to be active.
     */
    private String currentScreenId;

    /**
     * The identifier of the screen that is currently active.
     */
    private String activeScreenId;

    /**
     * The current active screen instance.
     */
    private Screen activeScreen;

    /**
     * Creates a new screen manager for the given application.
     *
     * @param application the application instance controlled by this manager
     */
    public ScreenManager(GameApplication application) {
        super(application);
    }

    /**
     * Initializes this screen manager.
     *
     * <p>This implementation does not perform initialization logic by default.</p>
     */
    @Override
    protected void init() {}

    /**
     * Updates the screen manager and the active screen.
     *
     * <p>This method first checks whether a screen transition is required. If
     * the requested screen differs from the active screen, the transition is
     * handled before updating the new active screen.</p>
     */
    @Override
    protected void update() {
        handleScreenTransition();

        if (activeScreen != null) {
            activeScreen.update();
        }
    }

    @Override
    protected void postUpdate() {}

    /**
     * Ends the active screen when the application is shutting down.
     *
     * <p>If a screen is active, this method calls its program-ending behavior,
     * then ends the screen and removes the active screen reference.</p>
     */
    @Override
    protected void end() {
        if (activeScreen != null) {
            activeScreen.endProgram();
            activeScreen.end();
            activeScreen = null;
        }
    }

    /**
     * Handles transitions between screens.
     *
     * <p>If the requested screen is already active, this method does nothing.
     * Otherwise, it ends the current active screen, updates the active screen
     * identifier, creates the new screen from its registered supplier, and starts
     * it.</p>
     *
     * @throws IllegalArgumentException if the requested screen identifier is not
     *                          registered.
     */
    private void handleScreenTransition() {
        if (Objects.equals(activeScreenId, currentScreenId)) {
            return;
        }

        if (activeScreen != null) {
            activeScreen.endScreen();
            activeScreen.end();
            activeScreen = null;
        }

        activeScreenId = currentScreenId;

        if (activeScreenId == null) {
            return;
        }

        Supplier<? extends Screen> supplier = screens.get(activeScreenId);

        if (supplier == null) {
            throw new IllegalArgumentException("The screen '" + activeScreenId + "' doesn't exist.");
        }

        activeScreen = supplier.get();

        activeScreen.start();
    }

    /**
     * Registers a screen supplier with the specified identifier.
     *
     * <p>If another screen supplier was already registered with the same
     * identifier, it will be replaced.</p>
     *
     * @param identifier the identifier used to register and access the screen.
     * @param screenSupplier the supplier that creates the screen instance.
     * @return this screen manager instance, allowing method chaining.
     * @see #registerScreen(Supplier)
     */
    public ScreenManager registerScreen(String identifier, Supplier<? extends Screen> screenSupplier) {
        this.screens.put(identifier, screenSupplier);
        return this;
    }

    /**
     * Registers a screen supplier with a generated identifier. The identifier
     * is generated with the simply name class.
     *
     * <p>If another screen supplier was already registered with the same
     * identifier, it will be replaced.</p>
     *
     * @param screenSupplier the supplier that creates the screen instance.
     * @return this screen manager instance, allowing method chaining.
     * @see #registerScreen(String, Supplier)
     */
    public ScreenManager registerScreen(Supplier<? extends Screen> screenSupplier) {
        this.screens.put(screenSupplier.getClass().getSimpleName(), screenSupplier);
        return this;
    }

    /**
     * Sets the main screen of the application.
     *
     * <p>The given identifier must belong to a registered screen. This method
     * only changes the requested screen identifier. The actual screen transition
     * is handled during the next update cycle.</p>
     *
     * @param identifier the identifier of the registered screen to use as main
     *                   screen.
     * @return this screen manager instance, allowing method chaining.
     * @throws IllegalArgumentException if the given screen identifier is not
     * registered.
     */
    public ScreenManager setMainScreen(String identifier) {
        if (!screens.containsKey(identifier)) {
            throw new IllegalArgumentException("The screen '" + identifier + "' doesn't exist.");
        }

        this.currentScreenId = identifier;
        return this;
    }

    /**
     * Requests a screen change using the given identifier.
     *
     * <p>The given identifier must belong to a registered screen. This method
     * only updates the requested screen identifier. The transition is performed
     * during the next update cycle.</p>
     *
     * @param identifier the identifier of the screen to activate.
     * @return this screen manager instance, allowing method chaining.
     * @throws IllegalArgumentException if the given screen identifier is not
     * registered.
     */
    public ScreenManager setCurrentScreen(String identifier) {
        if (!screens.containsKey(identifier)) {
            throw new IllegalArgumentException("The screen '" + identifier + "' doesn't exist.");
        }

        this.currentScreenId = identifier;
        return this;
    }

    /**
     * Returns the current active screen instance.
     *
     * <p>This method may return {@code null} if no screen has been activated
     * yet.</p>
     *
     * @return the active screen, or {@code null} if no screen is active.
     */
    public Screen getCurrentScreen() {
        return activeScreen;
    }
}