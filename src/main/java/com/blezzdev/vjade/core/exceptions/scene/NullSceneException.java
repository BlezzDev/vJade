package com.blezzdev.vjade.core.exceptions.scene;

import com.blezzdev.vjade.core.exceptions.VJadeException;

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
