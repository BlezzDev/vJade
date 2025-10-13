package com.blezzdev.vjade.core.manager.input;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {
    private long window;

    private final Map<String, int[]> bindInputs = new HashMap<>();

    private final boolean[] prevKeys = new boolean[GLFW_KEY_LAST];
    private final boolean[] prevMouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];

    private final boolean[] keys = new boolean[GLFW_KEY_LAST];
    private final boolean[] mouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];

    private double mouseX, mouseY;
    private double scrollX, scrollY;
    private long cursor = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);

    public void init(long window) {
        this.window = window;

        // Keyboard callback.

        glfwSetKeyCallback(window, (win, key, scancode, action, mods) -> {
            if (key >= 0 && key < keys.length) {
                keys[key] = action != GLFW_RELEASE;
            }
        });

        // Mouse callback.

        glfwSetMouseButtonCallback(window, (win, button, action, mods) -> {
            if (button >= 0 && button < mouseButtons.length) {
                mouseButtons[button] = action != GLFW_RELEASE;
            }
        });


        // Movement mouse callback.

        glfwSetCursorPosCallback(window, (win, xpos, ypos) -> {
            mouseX = xpos;
            mouseY = ypos;
        });

        // Wheel mouse callback.

        glfwSetScrollCallback(window, (win, xoffset, yoffset) -> {
            scrollX += xoffset;
            scrollY += yoffset;
        });
    }

    public void update() {
        System.arraycopy(keys, 0, prevKeys, 0, keys.length);
        System.arraycopy(mouseButtons, 0, prevMouseButtons, 0, mouseButtons.length);
    }

    // Public boolean methods for register inputs.

    public boolean isKeyJustDown(String action) {
        boolean isActionJustDown = false;
        for (int i : bindInputs.get(action)) {
            if (keys[i] && !prevKeys[i]) {
                isActionJustDown = true;
                break;
            }
        }
        return isActionJustDown;
    }

    public boolean isKeyJustUp(String action) {
        boolean isActionJustUp = false;
        for (int i : bindInputs.get(action)) {
            if (!keys[i] && prevKeys[i]) {
                isActionJustUp = true;
                break;
            }
        }
        return isActionJustUp;
    }

    public boolean isKeyJustDown(int key) {
        return keys[key] && !prevKeys[key];
    }

    public boolean isKeyJustUp(int key) {
        return !keys[key] && prevKeys[key];
    }


    public boolean isKeyDown(int key) {
        return keys[key];
    }
    public boolean isKeyDown(String action) {
        boolean isActionDown = false;
        for (int i : bindInputs.get(action)) {
            if (keys[i]) {
                isActionDown = true;
                break;
            }
        }
        return isActionDown;
    }

    public boolean isKeyUp(int key) {
        return !keys[key];
    }
    public boolean isKeyUp(String action) {
        boolean isActionDown = true;
        for (int i : bindInputs.get(action)) {
            if (keys[i]) {
                isActionDown = false;
                break;
            }
        }
        return isActionDown;
    }

    public boolean isMouseButtonDown(int button) {
        return mouseButtons[button];
    }
    public boolean isMouseButtonUp(int button) {
        return !mouseButtons[button];
    }

    public Vec2 getMousePos() {
        return new Vec2( (float) mouseX, (float) mouseY);
    }

    public double getScrollX() {
        return scrollX;
    }
    public double getScrollY() {
        return scrollY;
    }
    public void resetScroll() {
        scrollX = 0;
        scrollY = 0;
    }

    public void bindAction(String action, int[] inputs) {
        bindInputs.put(action, inputs);
    }

    public void setCursor(int cursor) {
        this.cursor = glfwCreateStandardCursor(cursor);
        glfwSetCursor(window, this.cursor);
    }
}

