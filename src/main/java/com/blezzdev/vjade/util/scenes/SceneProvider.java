package com.blezzdev.vjade.util.scenes;

import java.util.HashMap;
import java.util.Map;

public class SceneProvider {
    private static final Map<Integer, Scene> SCENES_PROVIDER = new HashMap<>();
    private static int idProvider = -1;
    private static int idMainScene;

    public int register(Scene scene) {
        idProvider++;

        SCENES_PROVIDER.put(idProvider, scene);
        return idProvider;
    }

    public Map<Integer, Scene> getScenesProvider() { return SCENES_PROVIDER; }
    public Scene getMainScene() { return SCENES_PROVIDER.get(idMainScene); }
    public int getIdMainScene() { return idMainScene; }

    public void setMainScene(int id) { idMainScene = id; }
}
