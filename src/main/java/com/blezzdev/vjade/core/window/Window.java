package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.objects.build.Screen;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.render.Texture;

import java.util.function.Supplier;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

class Window<T extends Window<T>> extends WindowBuilder {
    protected final WindowLogic windowLogic = new WindowLogic(this);

    private final Monitor monitor = new Monitor();
    private final WindowManager windowManager = new WindowManager();

    private final int[] width = new int[]{800};
    private final int[] height = new int[]{600};
    private final int[] x = new int[]{0};
    private final int[] y = new int[]{30};
    private String title = "vJade window.";
    private Color backgroundColor = new Color(1, 1, 1);
    private Icon icon = new Icon(glWindow);

    private int decorations = 1;
    private int resizable = 1;
    private int visible = 1;
    private int vsync = 1;

    public Window() {
        super();

        setVertexShader(VJade.DEFAULT_VERTEX_SHADER);
        setFragemtShader(VJade.DEFAULT_FRAGMENT_SHADER);

        windowManager.getInput().init(glWindow);
    }

    private void configureDetails() {
        glfwSwapInterval(vsync);

        glEnable(GL_TEXTURE_2D);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_CULL_FACE);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void run() {
        configureDetails();

        // Set the clear color.

        glClearColor(backgroundColor.getRed(),
                backgroundColor.getGreen(),
                backgroundColor.getBlue(),
                backgroundColor.getAlpha());

        windowLogic.init();
    }

    @SuppressWarnings("unchecked")
    public T maximized() {
        glfwMaximizeWindow(glWindow);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T centered() {
        setPosition((int) ((getMonitor().getSize().x - getSize().x) / 2),
                (int) ((getMonitor().getSize().y - getSize().y) / 2));
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T minimized() {
        glfwIconifyWindow(glWindow);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T fullscreen() {
        setPosition(0, 0);
        setSize(getMonitor().getSize());
        setDecorations(false);
        setResizable(false);

        glfwSetWindowMonitor(glWindow, glfwGetPrimaryMonitor(), 0, 0,
                (int) getMonitor().getSize().x, (int) getMonitor().getSize().y,
                getMonitor().getRefreshRate());

        return (T) this;
    }

    public void shutdown() {
        windowLogic.shutdown();
    }

    public Monitor getMonitor() { return monitor; }
    public int getFps() { return windowLogic.getFps(); }
    public WindowManager getManagers() { return windowManager; }

    @SuppressWarnings("unchecked")
    public T setPosition(int x, int y) {
        glfwSetWindowPos(glWindow, x, y);
        glfwGetWindowPos(glWindow, this.x, this.y);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setSize(Vec2 size) { setSize((int) size.x, (int) size.y); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setSize(int width, int height) {
        glfwSetWindowSize(glWindow, width, height);
        glfwGetWindowSize(glWindow, this.width, this.height);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTitle(String title) {
        glfwSetWindowTitle(glWindow, title);
        this.title = title;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setIcon(String path) { setIcon(new Texture(path)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setIcon(Texture icon) {
        this.icon.loadIcon(icon);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setResizable(boolean resizable) {
        glfwSetWindowAttrib(glWindow, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        this.resizable = glfwGetWindowAttrib(glWindow, GLFW_RESIZABLE);
        return (T) this;
    }

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

    @SuppressWarnings("unchecked")
    public T setBackgroundColor(Color color) {
        this.backgroundColor = color;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setDecorations(boolean decorations) {
        glfwSetWindowAttrib(glWindow, GLFW_DECORATED, decorations ? GLFW_TRUE : GLFW_FALSE);
        this.decorations = glfwGetWindowAttrib(glWindow, GLFW_DECORATED);
        return (T) this;
    }

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

    public Texture getIcon() { return icon.getTexture(); }

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

    @SuppressWarnings("unchecked")
    public T addScreen(Supplier<Screen> screen) { addScreen(screen, screen.getClass().getSimpleName()); return (T) this; }
    @SuppressWarnings("unchecked")
    public T addScreen(Supplier<Screen> screen, String identifier) {
        getManagers().getScreen().register(screen, identifier);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setMainScreen(String identifier) {
        getManagers().getScreen().setMainScreen(identifier);
        return (T) this;
    }

    public void changeScreen(String identifier) {
        getManagers().getScreen().setCurrentScreen(identifier);
    }
}
