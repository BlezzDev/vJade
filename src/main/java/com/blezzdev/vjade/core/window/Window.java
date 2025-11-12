package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.objects.build.Screen;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.canvas.Icon;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vec2;

import java.util.function.Supplier;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/*
* Window This is the raw code for the Engine class, which manages the window properties.
* */

class Window<T extends Window<T>> extends WindowBuilder {
    protected final WindowLogic windowLogic = new WindowLogic(this);

    private final Monitor monitor = new Monitor();
    private final Managers windowManager = new Managers();

    private final int[] width = new int[]{800};
    private final int[] height = new int[]{600};
    private final int[] x = new int[]{0};
    private final int[] y = new int[]{30};
    private String title = "vJade window.";
    private Color backgroundColor = new Color(1, 1, 1);
    private Icon icon;

    private int decorations = 1;
    private int resizable = 1;
    private int visible = 1;
    private int vsync = 1;

    public Window() {
        super();

        setVertexShader(VJade.DEFAULT_VERTEX_SHADER);
        setFragemtShader(VJade.DEFAULT_FRAGMENT_SHADER);
    }

    private void configureDetails() {
        glfwSwapInterval(vsync);

        glDisable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    void run() {
        configureDetails();

        if (VJade.isDebugMode()) {
            System.out.printf("""
                             > Window properties:
                              
                               | > Size: %s
                               | > Position: %s
                               | > Icon: %s
                               | > Title: %s
                               |
                               | > Resizable: %s
                               | > VSync: %s
                               | > Decorations: %s
                               | > Visible: %s
                            %n""", getSize(), getPosition(), getIcon().getResourcePath(), getTitle(),
                    isResizable(), isVSync(), isDecorated(), isVisible()
                    );
        }

        // Set the clear color.

        glClearColor(backgroundColor.getRed(),
                backgroundColor.getGreen(),
                backgroundColor.getBlue(),
                backgroundColor.getAlpha());

        windowLogic.init();
    }

    /**
     * {@link #maximized()} is a management method that maximizes the size
     * of the window, that is, scales the window to the size of the monitor.
     *
     * <p>There is its counterpart, which is {@link #minimized()}</p>
     * */

    @SuppressWarnings("unchecked")
    public T maximized() {
        glfwMaximizeWindow(glWindow);
        return (T) this;
    }

    /**
     * {@link #centered()} is a management method that centers the window
     * depending on the resolution of the monitor where the window is located.
     * */

    @SuppressWarnings("unchecked")
    public T centered() {
        setPosition((int) ((getMonitor().getResolution().x - getSize().x) / 2),
                (int) ((getMonitor().getResolution().y - getSize().y) / 2));
        return (T) this;
    }

    /**
     * {@link #minimized()} is a management method that minimizes the size
     * of the window, that is, hide or conceal the window.
     *
     * <p>There is its counterpart, which is {@link #maximized()}</p>
     * */

    @SuppressWarnings("unchecked")
    public T minimized() {
        glfwIconifyWindow(glWindow);
        return (T) this;
    }

    /**
     * {@link #fullscreen()} is a management method that configure the window
     * so that it covers the entire monitor, including the taskbar, where the
     * window is the point of attention.
     * */

    @SuppressWarnings("unchecked")
    public T fullscreen() {
        setPosition(0, 0);
        setSize(getMonitor().getResolution());
        setDecorations(false);
        setResizable(false);

        glfwSetWindowMonitor(glWindow, glfwGetPrimaryMonitor(), 0, 0,
                (int) getMonitor().getResolution().x, (int) getMonitor().getResolution().y,
                getMonitor().getRefreshRate());

        return (T) this;
    }

    /**
     * {@link #shutdown()} is a management method that forces the program's life
     * cycle to end, that is, it destroys the window.
     * */

    public void shutdown() {
        windowLogic.shutdown();
    }

    public Monitor getMonitor() { return monitor; }
    public int getFps() { return windowLogic.getFps(); }
    public Managers getManagers() { return windowManager; }

    /**
     * {@link #setPosition(Vec2)} is a property method that sets the position of the window according to the monitor where the window is located.
     *
     * <p>This method updates the window position and stores the resulting
     * coordinates internally. Returns the current instance to allow method chaining.</p>
     *
     * @param position a {@link Vec2} representing the X and Y position of the window.
     * @return this instance for chained calls.
     * @see #setPosition(int, int)
     */

    @SuppressWarnings("unchecked")
    public T setPosition(Vec2 position) { setPosition((int) position.x, (int) position.y); return (T) this; }

    /**
     * {@link #setPosition(int, int)} is a property method that sets the position of the window according to the monitor where the window is located.
     *
     * <p>This method updates the window position and stores the resulting
     * coordinates internally. Returns the current instance to allow method chaining.</p>
     *
     * @param x an int representing the X position of the window.
     * @param y an int representing the Y position of the window.
     * @return this instance for chained calls.
     * @see #setPosition(Vec2)
     */

    @SuppressWarnings("unchecked")
    public T setPosition(int x, int y) {
        glfwSetWindowPos(glWindow, x, y);
        glfwGetWindowPos(glWindow, this.x, this.y);
        return (T) this;
    }

    /**
     * {@link #setSize(Vec2)} is a property method that sets the size of the window according to the monitor where the window is located.
     *
     * <p>This method updates the window dimensions and stores the resulting
     * sizes internally. Returns the current instance to allow method chaining.</p>
     *
     * @param size a {@link Vec2} representing the dimensions of the window.
     * @return this instance for chained calls.
     * @see #setSize(int, int)
     */

    @SuppressWarnings("unchecked")
    public T setSize(Vec2 size) { setSize((int) size.x, (int) size.y); return (T) this; }

    /**
     * {@link #setSize(int, int)} is a property method that sets the size of the window in according to the monitor where the window is located.
     *
     * <p>This method updates the window dimensions and stores the resulting
     * sizes internally. Returns the current instance to allow method chaining.</p>
     *
     * @param width an int representing the width of the window.
     * @param height an int representing the height of the window.
     * @return this instance for chained calls.
     * @see #setSize(Vec2)
     */

    @SuppressWarnings("unchecked")
    public T setSize(int width, int height) {
        glfwSetWindowSize(glWindow, width, height);
        glfwGetWindowSize(glWindow, this.width, this.height);
        return (T) this;
    }

    /**
     * {@link #setTitle(String)} is a property method that sets the title of the window visible in the top bar of the window (As long as the borders are enabled).
     *
     * <p>This method updates the window title and stores the data
     * internally. Returns the current instance to allow method chaining.</p>
     *
     * @param title a {@link String} representing whatever the program is called.
     * @return this instance for chained calls.
     */

    @SuppressWarnings("unchecked")
    public T setTitle(String title) {
        glfwSetWindowTitle(glWindow, title);
        this.title = title;
        return (T) this;
    }

    /**
     * {@link #setIcon(String)} is a property method that sets the icon of the window visible in the top bar of the window (As long as the borders are enabled).
     *
     * <p>This method updates the window icon and stores the resulting
     * image data internally. Returns the current instance to allow method chaining.</p>
     *
     * @param resourcePath a {@link String} representing the resource path where the icon texture is stored.
     * @return this instance for chained calls.
     * @see #setIcon(Icon)
     */

    @SuppressWarnings("unchecked")
    public T setIcon(String resourcePath) { setIcon(new Icon(resourcePath)); return (T) this; }

    /**
     * {@link #setIcon(Icon)} is a property method that sets the icon of the window visible in the top bar of the window (As long as the borders are enabled).
     *
     * <p>This method updates the window icon and stores it. Returns the
     * current instance to allow method chaining.</p>
     *
     * @param icon an {@link Icon} representing the icon in a complete format.
     * @return this instance for chained calls.
     * @see #setIcon(String)
     */

    @SuppressWarnings("unchecked")
    public T setIcon(Icon icon) {
        this.icon = icon;

        this.icon.load();
        this.icon.applyToWindow(glWindow);

        return (T) this;
    }

    /**
     * {@link #setResizable(boolean)} is a property method that determines whether the window can be manually resized.
     *
     * <p>This method updates the resizable attribute and stores the value. Returns the
     * current instance to allow method chaining.</p>
     *
     * @param resizable a boolean representing the state of the manual scalability.
     * @return this instance for chained calls.
     */

    @SuppressWarnings("unchecked")
    public T setResizable(boolean resizable) {
        glfwSetWindowAttrib(glWindow, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        this.resizable = glfwGetWindowAttrib(glWindow, GLFW_RESIZABLE);
        return (T) this;
    }

    /**
     * {@link #setVisible(boolean)} is a property method that determines the visibility of the window.
     * If is true, will behave like a regular window, but if is false, Your identity
     * the screen and in the taskbar will disappear.
     *
     * <p>This method updates the visibility attribute and stores the value. Returns the
     * current instance to allow method chaining.</p>
     *
     * @param visible a boolean representing the state of the visibility.
     * @return this instance for chained calls.
     */

    @SuppressWarnings("unchecked")
    public T setVisible(boolean visible) {
        if (visible) {
            glfwShowWindow(glWindow);
        } else {
            glfwHideWindow(glWindow);
        }

        this.visible = visible ? 1 : 0;
        return (T) this;
    }

    /**
     * {@link #setBackgroundColor(Color)} is a property method that sets the base color in the background.
     *
     * <p>This method updates the background color and stores our red, green, and blue channels values. Returns the
     * current instance to allow method chaining.</p>
     *
     * @param color a {@link Color} representing the color in a readable format.
     * @return this instance for chained calls.
     */

    @SuppressWarnings("unchecked")
    public T setBackgroundColor(Color color) {
        this.backgroundColor = color;
        return (T) this;
    }

    /**
     * {@link #setDecorations(boolean)} is a property method that determines whether the window borders
     * will be removed or not. The design depends on the operating system. If you want a special design,
     * we recommend disabling it and creating your own border system.
     *
     * <p>This method updates the decorated attribute and stores the value. Returns the
     * current instance to allow method chaining.</p>
     *
     * @param decorations a boolean representing the state of the decorations.
     * @return this instance for chained calls.
     */

    @SuppressWarnings("unchecked")
    public T setDecorations(boolean decorations) {
        glfwSetWindowAttrib(glWindow, GLFW_DECORATED, decorations ? GLFW_TRUE : GLFW_FALSE);
        this.decorations = glfwGetWindowAttrib(glWindow, GLFW_DECORATED);
        return (T) this;
    }

    /**
     * {@link #setVSync(boolean)} is a property method that determines the state of the vertical sync.
     * Vertical synchronization seeks to synchronize the frames of a graphics application with the refresh
     * rate of a monitor, which prevents screen tearing.
     *
     * <p>This method stores the value and when is running automatically sets the vertical sync depends on the value stored. Returns the
     * current instance to allow method chaining.</p>
     *
     * @param vsync a boolean representing the value stored for the vertical sync result.
     * @return this instance for chained calls.
     */

    @SuppressWarnings("unchecked")
    public T setVSync(boolean vsync) {
        this.vsync = vsync ? GLFW_TRUE : GLFW_FALSE;
        return (T) this;
    }

    public Vec2 getPosition() {
        glfwGetWindowPos(glWindow, x, y);
        return new Vec2(x[0], y[0]);
    }

    public Vec2 getSize() {
        glfwGetWindowSize(glWindow, width, height);
        return new Vec2(width[0], height[0]);
    }

    public String getTitle() {
        return title;
    }

    public Icon getIcon() {
        return icon;
    }

    public Color getBackgroundColor() { return backgroundColor; }

    public boolean isVisible() {
        this.visible = glfwGetWindowAttrib(glWindow, GLFW_VISIBLE);
        return visible != 0;
    }

    public boolean isResizable() {
        this.resizable = glfwGetWindowAttrib(glWindow, GLFW_RESIZABLE);
        return resizable != 0;
    }

    public boolean isDecorated() {
        this.decorations = glfwGetWindowAttrib(glWindow, GLFW_DECORATED);
        return decorations != 0;
    }

    public boolean isVSync() {
        return vsync != 0;
    }

    /**
     * {@link #addScreen(Supplier)} is a method that stores a screen to be used later.
     *
     * <p>This method sends the class to the {@link ScreenManager} where it is stored in a list with his
     * class name how identifier.</p>
     *
     * @param screen a {@link Supplier<Screen>} representing the value stored for the manager.
     * @return this instance for chained calls.
     * @see #addScreen(Supplier, String)
     */

    @SuppressWarnings("unchecked")
    public T addScreen(Supplier<Screen> screen) { addScreen(screen, screen.getClass().getSimpleName()); return (T) this; }

    /**
     * {@link #addScreen(Supplier, String)} is a method that stores a screen to be used later.
     *
     * <p>This method sends the class to the {@link ScreenManager} where it is stored in a list with a custom
     * identifier.</p>
     *
     * @param screen a {@link Supplier<Screen>} representing the value stored for the manager.
     * @param identifier a {@link String} representing the identifier for the screen in the list.
     * @return this instance for chained calls.
     * @see #addScreen(Supplier)
     */

    @SuppressWarnings("unchecked")
    public T addScreen(Supplier<Screen> screen, String identifier) {
        getManagers().getScreen().register(screen, identifier);
        return (T) this;
    }

    /**
     * {@link #setMainScreen(String)} is a method that sets the scene to be executed as an initializer.
     *
     * <p>This method searches for the scene that will be executed when the program starts, using the
     * identifier entered.</p>
     *
     * @param identifier a {@link String} representing the identifier for the searched screen.
     * @return this instance for chained calls.
     * */

    @SuppressWarnings("unchecked")
    public T setMainScreen(String identifier) {
        getManagers().getScreen().setMainScreen(identifier);
        return (T) this;
    }

    /**
     * {@link #changeScreen(String)} is a method that sets the scene to be executed.
     *
     * <p>This method searches for the scene that will be executed, using the
     * identifier entered.</p>
     *
     * @param identifier a {@link String} representing the identifier for the searched screen.
     * @return this instance for chained calls.
     * */

    public void changeScreen(String identifier) {
        getManagers().getScreen().setCurrentScreen(identifier);
    }
}
