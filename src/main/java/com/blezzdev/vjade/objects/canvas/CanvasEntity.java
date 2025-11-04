package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.Shader;
import com.blezzdev.vjade.tools.canvas.Texture;

public class CanvasEntity<T extends CanvasEntity<T>> extends CanvasItem<T> {
    private Shader shader;
    private Texture texture;

    @SuppressWarnings("unchecked")
    public T setShader(Shader shader) {
        this.shader = shader;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTexture(String resourcePath) { setTexture(new Texture(resourcePath)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setTexture(Texture texture) {
        this.texture = texture;
        return (T) this;
    }

    public Shader getShader() {
        return shader;
    }

    public Texture getTexture() {
        return texture;
    }
}
