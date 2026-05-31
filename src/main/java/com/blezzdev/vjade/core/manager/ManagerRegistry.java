package com.blezzdev.vjade.core.manager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The {@code ManagerRegistry} class stores and manages different
 * {@link Manager} instances used by the
 * {@link com.blezzdev.vjade.GameApplication}.
 *
 * <p>This class allows managers to be registered by their concrete class type,
 * retrieved when needed, and executed through their main lifecycle methods:
 * {@code init}, {@code update}, and {@code end}.</p>
 *
 * <p>Each registered manager is stored using its runtime class as the key.
 * This means only one manager instance can exist for each manager class.</p>
 *
 * @since   0.1
 * @version 0.1
 * @author  Jesus David Alfaro Marenco
 */
public class ManagerRegistry {

    /**
     * Stores all registered managers using their concrete class as the key.
     */
    private final Map<Class<? extends Manager>, Manager> registries = new LinkedHashMap<>();

    /**
     * Registers a manager instance in the registry.
     *
     * <p>The manager is stored using its runtime class as the key. If another
     * manager of the same class was already registered, it will be replaced.</p>
     *
     * @param <T> the type of manager to register.
     * @param manager the manager instance to register.
     * @return this registry instance, allowing method chaining.
     */
    public final <T extends Manager> ManagerRegistry register(T manager) {
        registries.put(manager.getClass(), manager);
        return this;
    }

    /**
     * Returns a registered manager by its class type.
     *
     * @param <T> the expected manager type.
     * @param type the class type of the manager to retrieve.
     * @return the registered manager instance, or {@code null} if not found.
     * @throws NullPointerException if the searched manager doesn't exist.
     */
    @SuppressWarnings("unchecked")
    public <T extends Manager> T get(Class<T> type) {
        if (registries.get(type) == null) throw new NullPointerException(type.getSimpleName() + " doesn't exist.");
        return (T) registries.get(type);
    }

    /**
     * Starts all registered managers.
     *
     * <p>This method calls {@link Manager#init()} on every manager currently
     * stored in the registry.</p>
     */
    public void start() {
        for (Manager manager : registries.values()) {
            manager.init();
        }
    }

    /**
     * Updates all registered managers.
     *
     * <p>This method calls {@link Manager#update()} on every manager currently
     * stored in the registry.</p>
     */
    public void update() {
        for (Manager manager : registries.values()) {
            manager.update();
        }
    }

    /**
     * Updates all registered managers.
     *
     * <p>This method calls {@link Manager#postUpdate()} on every manager currently
     * stored in the registry.</p>
     */
    public void postUpdate() {
        for (Manager manager : registries.values()) {
            manager.postUpdate();
        }
    }

    /**
     * Ends all registered managers and clears the registry.
     *
     * <p>This method calls {@link Manager#end()} on every registered manager.
     * After all managers have ended, the registry is cleared.</p>
     */
    public void end() {
        for (Manager manager : registries.values()) {
            manager.end();
        }
        registries.clear();
    }
}