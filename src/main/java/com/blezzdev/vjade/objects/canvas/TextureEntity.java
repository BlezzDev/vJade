package com.blezzdev.vjade.objects.canvas;

import com.blezzdev.vjade.objects.build.Shader;
import com.blezzdev.vjade.tools.data.render.Texture;

public class TextureEntity<T extends TextureEntity<T>> extends CanvasItem<T>{
    private Texture texture;
    private Shader shader;

    @SuppressWarnings("unchecked")
    public T setTexture(String path) { setTexture(new Texture(path)); return (T) this; }
    @SuppressWarnings("unchecked")
    public T setTexture(Texture texture) {
        this.texture = texture;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setShader(Shader shader) {
        this.shader = shader;
        return (T) this;
    }

    public Texture getTexture() {
        return texture;
    }

    public Shader getShader() {
        return shader;
    }
}
