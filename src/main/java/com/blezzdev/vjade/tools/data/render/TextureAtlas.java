package com.blezzdev.vjade.tools.data.render;

import com.blezzdev.vjade.tools.data.geometry.Vec2;

import java.util.*;

/// TextureAtlas is a data that helps in the management of multiple textures in the same large texture.

public class TextureAtlas {
    private final Map<String, TextureRegion> regions = new HashMap<>();

    private Vec2 getLastRegion() {
        Vec2 position = new Vec2();

        for (TextureRegion region : regions.values()) {
            position.add(region.getWidth(), 0);
        }

        return position;
    }

    public void newRegion(TextureRegion texture) {
        texture.position = getLastRegion();

        String identifier = UUID.randomUUID().toString();
        texture.uuid = identifier;
        regions.put(identifier, texture);
    }

    public void deleteRegion(String uuid) {
        regions.remove(uuid);
    }

    public TextureRegion getRegion(String uuid) {
        return regions.get(uuid);
    }

    public Collection<TextureRegion> getRegions() {
        return regions.values();
    }
}