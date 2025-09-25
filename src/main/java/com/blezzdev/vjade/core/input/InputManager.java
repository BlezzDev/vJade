package com.blezzdev.vjade.core.input;

import com.blezzdev.vjade.tools.data.geometry.Vector2;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {
    private Map<String, int[]> bindedInputs = new HashMap<>();
    private boolean[] keys = new boolean[GLFW_KEY_LAST];
    private boolean[] mouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private double mouseX, mouseY;
    private double scrollX, scrollY;

    public void init(long window) {

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

    // Public boolean methods for register inputs.

    public boolean isKeyDown(int key) {
        return keys[key];
    }
    public boolean isKeyDown(String action) {
        int[] inputs = bindedInputs.get(action);

        boolean isActionDown = false;
        for (int i : inputs) {
            if (keys[i]) {
                isActionDown = true;
            }
        }
        return isActionDown;
    }

    public boolean isKeyUp(int key) {
        return !keys[key];
    }
    public boolean isKeyUp(String action) {
        int[] inputs = bindedInputs.get(action);

        boolean isActionDown = true;
        for (int i : inputs) {
            if (keys[i]) {
                isActionDown = false;
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

    public Vector2 getMousePos() {
        return new Vector2( (float) mouseX, (float) mouseY);
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
        bindedInputs.put(action, inputs);
    }
}

