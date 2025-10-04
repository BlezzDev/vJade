package com.blezzdev.vjade.objects.build;

public class Screen extends VJObject {
    private String identifier;

    public Screen() {}

    public enum Behavior {
        CONTINUITY, INDIVIDUAL
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}