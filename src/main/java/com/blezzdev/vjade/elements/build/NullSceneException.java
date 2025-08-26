package com.blezzdev.vjade.elements.build;

import com.blezzdev.vjade.tools.VJadeException;

public class NullSceneException extends VJadeException {
    private final String sceneName;

    public NullSceneException(String sceneName) {
        super("Scene '" + sceneName + "' not found.");
        this.sceneName = sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }
}
