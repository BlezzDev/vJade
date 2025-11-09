package com.blezzdev.vjade.core.manager.canvas.batch;

import com.blezzdev.vjade.tools.canvas.Shader;
import com.blezzdev.vjade.tools.canvas.Texture;
import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.geometry.specialized.Pivot;
import com.blezzdev.vjade.util.types.Behavior;

class DrawCommand {
    Shader shader;
    Texture texture;
    Vec2 position, size, view;
    Pivot pivot;
    Color color;
    Behavior behavior;
    float rotation, zIndex, zoom;

    DrawCommand(Shader shader, Texture texture, Vec2 position, Vec2 size, Pivot pivot,
                Color color, Behavior behavior, float rotation,
                float zIndex, float zoom, Vec2 view) {
        this.shader = shader;
        this.texture = texture;
        this.position = new Vec2(position);
        this.size = new Vec2(size);
        this.pivot = pivot;
        this.color = color;
        this.behavior = behavior;
        this.rotation = rotation;
        this.zIndex = zIndex;
        this.zoom = zoom;
        this.view = new Vec2(view);
    }
}
