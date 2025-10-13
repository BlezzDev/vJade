package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.build.Screen;
import com.blezzdev.vjade.objects.build.ScreenUI;

import java.util.HashMap;
import java.util.Map;

public class UserInterfaceManager {
    private final Map<Screen, ScreenUI> linkedList = new HashMap<>();

    public void linkScreen(Screen src, ScreenUI scrUI) {
        linkedList.put(src, scrUI);
    }
}
