package com.blezzdev.vjade.core.manager.canvas;

import com.blezzdev.vjade.GameApplication;
import com.blezzdev.vjade.core.manager.Manager;
import com.blezzdev.vjade.util.canvas.Shader;

public class CanvasManager extends Manager {
    private final Shader mainShader = MainShaderFactory.build();

    /**
     * Creates a new manager associated with the given application.
     *
     * @param application the application instance controlled or used by this manager
     */
    public CanvasManager(GameApplication application) {
        super(application);
    }

    @Override
    protected void init() {
        MainShaderFactory.bindWindowScale(mainShader, application.getWindow());
    }

    @Override
    protected void update() {}
    @Override
    protected void postUpdate() {}

    @Override
    protected void end() {

    }

    public Shader getMainShader() { return mainShader; }
}
