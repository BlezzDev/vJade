package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.core.input.InputManager;
import com.blezzdev.vjade.core.manager.ScreenManager;
import com.blezzdev.vjade.objects.build.Screen;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vector2;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.*;

public class Window<T extends Window<T>> extends WindowBuilder {
    private final Monitor monitor = new Monitor();
    private final InputManager inputManager = new InputManager();
    private final WindowLogic windowLogic = new WindowLogic(this);
    private final ScreenManager screenManager = new ScreenManager();

    private final int[] width = new int[]{800};
    private final int[] height = new int[]{600};
    private final int[] x = new int[]{0};
    private final int[] y = new int[]{30};
    private String title = "vJade window.";
    private Color backgroundColor = new Color(1, 1, 1);

    private int decorations = 1;
    private int resizable = 1;
    private int visible = 1;
    private int vsync = 1;

    public enum State { FULLSCREEN, MAXIMIZE, CENTERED }

    public Window() {
        super();

        String DEFAULT_VERTEX_SHADER = """
                #version 330 core
                layout (location = 0) in vec3 vjPos;
                layout (location = 1) in vec2 vjTexCoord;
                
                uniform mat4 vjProjection;
                uniform mat4 vjModel;
                
                out vec2 TexCoord;
                
                void main()
                {
                    gl_Position = vjProjection * vjModel * vec4(vjPos, 1.0);
                    TexCoord = vjTexCoord;
                }
                """;

        setVertexShader(DEFAULT_VERTEX_SHADER);

        String DEFAULT_FRAGMENT_SHADER = """
                #version 330 core
                
                out vec4 vjFragColor;
                in vec2 TexCoord;
                
                uniform sampler2D vjDiffuseTex;
                uniform vec4 vjModulate;
                uniform bool vjUseTexture;
                
                void main() {
                    if (vjUseTexture) {
                        vjFragColor = texture(vjDiffuseTex, TexCoord) * vjModulate;
                    } else {
                        vjFragColor = vjModulate;
                    }
                }
                """;

        setFragemtShader(DEFAULT_FRAGMENT_SHADER);

        inputManager.init(glWindow);
    }

    private void configureDetails() {
        glfwShowWindow(glWindow);
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

        glClearColor(backgroundColor.r1,
                backgroundColor.g1,
                backgroundColor.b1,
                backgroundColor.a1);

        windowLogic.init();
    }

    @SuppressWarnings("unchecked")
    public T setState(State asm) {
        switch (asm) {
            case FULLSCREEN:
                fullscreen();
                break;
            case CENTERED:
                centered();
                break;
            case MAXIMIZE:
                maximize();
                break;
        }
        return (T) this;
    }

    private void maximize() {
        glfwMaximizeWindow(glWindow);
    }

    private void centered() {
        setPosition((int) ((getMonitor().getSize().x - getSize().x) / 2),
                (int) ((getMonitor().getSize().y - getSize().y) / 2));
    }

    private void fullscreen() {
        setPosition(0, 0);
        setSize(getMonitor().getSize());
        setDecorations(false);
        setResizable(false);

        glfwSetWindowMonitor(glWindow, glfwGetPrimaryMonitor(), 0, 0,
                (int) getMonitor().getSize().x, (int) getMonitor().getSize().y,
                getMonitor().getRefreshRate());
    }

    public WindowLogic getLogic() { return windowLogic; }
    public long getGLWindow() { return glWindow; }
    public int getFps() {
        return windowLogic.getFps();
    }

    @SuppressWarnings("unchecked")
    public T setPosition(int x, int y) {
        glfwSetWindowPos(glWindow, x, y);
        glfwGetWindowPos(glWindow, this.x, this.y);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setSize(Vector2 size) { setSize((int) size.x, (int) size.y); return (T) this; }
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
    public T setResizable(boolean resizable) {
        glfwSetWindowAttrib(glWindow, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        this.resizable = glfwGetWindowAttrib(glWindow, GLFW_RESIZABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setVisible(boolean visible) {
        glfwSetWindowAttrib(glWindow, GLFW_VISIBLE, visible ? GLFW_TRUE : GLFW_FALSE);
        this.visible = glfwGetWindowAttrib(glWindow, GLFW_VISIBLE);
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
    public T setVsync(boolean vsync) {
        this.vsync = vsync ? GLFW_TRUE : GLFW_FALSE;
        return (T) this;
    }

    public Vector2 getPosition() {
        glfwGetWindowPos(glWindow, x, y);
        return new Vector2(x[0], y[0]);
    }

    public Vector2 getSize() {
        glfwGetWindowSize(glWindow, width, height);
        return new Vector2(width[0], height[0]);
    }

    public String getTitle() {
        return title;
    }

    public Color getBackgroundColor() { return backgroundColor; }

    public Monitor getMonitor() { return monitor; }

    public InputManager getInput() { return inputManager; }

    public ScreenManager getScreenManager() { return screenManager; }

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

    public boolean isVsync() {
        return vsync != 0;
    }

    @SuppressWarnings("unchecked")
    public T addScreen(Screen screen) { addScreen(screen, screen.getClass().getSimpleName()); return (T) this; }
    @SuppressWarnings("unchecked")
    public T addScreen(Screen screen, String identifier) {
        screenManager.register(screen, identifier);
        screen.setIdentifier(identifier);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setScreenBehavior(Screen.Behavior behavior) {
        screenManager.setBehavior(behavior);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setMainScreen(String identifier) {
        screenManager.setMainScreen(identifier);
        return (T) this;
    }

    public void changeScreen(String identifier) {
        screenManager.setCurrentScreen(identifier);
    }
}
