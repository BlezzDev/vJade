package com.blezzdev.vjade.core.window;

/**
 * Encapsulates all utility configurations for managing a {@link Window}
 * instance.
 * <p>
 * This class serves as an entry point for a fluent API to easily configure
 * and manipulate window properties, such as positioning.
 * </p>
 *
 * @author  Jesus David Alfaro Marenco
 * @since   0.1
 * @version 0.1
 */
public class WindowHelper {
    private static Window windowModule;

    /**
     * Binds a specific window instance to this helper to init the configuration chain.
     *
     * @param window the window instance to be managed by this helper
     * @return a new configuration context containing quick setup methods
     */
    public static WindowHelperConfigurations with(Window window) {
        windowModule = window;
        return new WindowHelperConfigurations();
    }

    /**
     * Provides a fluent API context to chain configuration changes on a bound window.
     *
     * @author  Jesus David Alfaro Marenco
     * @since   0.1
     * @version 0.1
     */
    public static class WindowHelperConfigurations {

        /**
         * Centers the bound window horizontally and vertically on its current monitor.
         *
         * @return this configuration instance for method chaining
         */
        public WindowHelperConfigurations centered() {
            Monitor monitor = windowModule.getMonitor();
            WindowProperties properties = windowModule.getProperties();

            properties.setPosition(
                    (monitor.getWidth() - properties.getWidth()) / 2,
                    (monitor.getHeight() - properties.getHeight()) / 2
            );

            return this;
        }
    }
}