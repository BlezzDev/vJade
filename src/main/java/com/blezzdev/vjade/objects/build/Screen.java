package com.blezzdev.vjade.objects.build;

import com.blezzdev.vjade.objects.build.vjobj.VJObject;

public class Screen extends VJObject {
    private String identifier;

    public Screen() {}

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}