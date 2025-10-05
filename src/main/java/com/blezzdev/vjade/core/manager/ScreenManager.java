package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.build.Screen;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class ScreenManager {
    private final Map<String, Supplier<Screen>> screenList = new HashMap<>();

    private boolean firstScreenFrame = true;
    private String lastScreen;
    private String currentScreen;

    private Screen activeScreen;

    private void startScreenLifeCycle() {
        if (firstScreenFrame) {
            firstScreenFrame = false;
            activeScreen.start();
        }
    }

    private void finishScreenLifeCycle() {
        if (!Objects.equals(lastScreen, currentScreen)) {
            if (activeScreen != null) {
                activeScreen.end_scene();
                activeScreen.finish();
            }

            lastScreen = currentScreen;
            firstScreenFrame = true;

            activeScreen = screenList.get(currentScreen).get();
            activeScreen.setIdentifier(currentScreen);

            startScreenLifeCycle();
        }
    }

    public void screenLifeCycle(double deltaTime) {
        if (activeScreen == null) {
            activeScreen = screenList.get(currentScreen).get();
            activeScreen.setIdentifier(currentScreen);
        }

        startScreenLifeCycle();

        activeScreen.update(deltaTime);

        finishScreenLifeCycle();
    }

    public void destroy() {
        if (activeScreen != null) {
            activeScreen.end_program();
            activeScreen.finish();
        }
    }

    public void register(Supplier<Screen> screen, String identifier) {
        screenList.put(identifier, screen);
    }

    public Map<String, Supplier<Screen>> getList() {
        return screenList;
    }

    public void setMainScreen(String currentScreen) {
        setCurrentScreen(currentScreen);
    }

    public void setCurrentScreen(String currentScreen) {
        this.currentScreen = currentScreen;
    }
}
