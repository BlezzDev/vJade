package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.build.Screen;
import com.blezzdev.vjade.tools.VJade;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScreenManager {
    private final Map<String, Screen> screenList = new HashMap<>();

    private boolean firstScreenLoop = true;
    private String lastScreen;
    private String currentScreen;

    public boolean destroyed = false;

    public void screenProcesses(double deltaTime) {
        Screen screen = VJade.getContext().getScreenProvider().getList().get(currentScreen);

        if (screen != null) {
            if (firstScreenLoop) {
                firstScreenLoop = false;

                screen.start();
            }

            screen.update(deltaTime);

            if (!Objects.equals(currentScreen, lastScreen)) {
                firstScreenLoop = true;
                lastScreen = currentScreen;

                screen.end_scene();
                screen.finish();
            }

            if (destroyed) {
                screen.end_program();
                screen.finish();
            }
        }
    }

    public void register(Screen screen, String identifier) {
        screenList.put(identifier, screen);
    }

    public Map<String, Screen> getList() {
        return screenList;
    }

    public void setMainScreen(String currentScreen) {
        this.lastScreen = currentScreen;
        setCurrentScreen(currentScreen);
    }

    public void setCurrentScreen(String currentScreen) {
        this.currentScreen = currentScreen;
    }
}
