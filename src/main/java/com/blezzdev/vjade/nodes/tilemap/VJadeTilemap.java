package com.blezzdev.vjade.nodes.tilemap;

import com.blezzdev.vjade.nodes.VJadeVisualNode;

import java.util.HashMap;
import java.util.Map;

public class VJadeTilemap extends VJadeVisualNode {
    private static final Map<String, VJadeTilemap> REGISTERED_TILES = new HashMap<>();

    public VJadeTilemap(String identifier) {
        REGISTERED_TILES.put(identifier, this);
    }

    public void unregister(String identifier) {
        REGISTERED_TILES.remove(identifier);
    }
}
