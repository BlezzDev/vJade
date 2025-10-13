package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.objects.build.item.VJObject;

public class Screen extends VJObject {
    private String identifier;

    public Screen() {}

    public void linkUI(ScreenUI srcUI) {
        if (srcUI.getIdentifier() == null) {
            throw new RuntimeException("This user interface screen has been registered before.");
        } else {
            getUserInterface().linkScreen(this, srcUI);
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}