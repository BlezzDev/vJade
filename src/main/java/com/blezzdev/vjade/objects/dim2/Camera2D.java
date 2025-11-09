package com.blezzdev.vjade.objects.dim2;

import com.blezzdev.vjade.core.manager.canvas.CanvasManager;
import com.blezzdev.vjade.objects.build.item.VJPoint;
import com.blezzdev.vjade.tools.VJade;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.geometry.specialized.Target;

public class Camera2D extends VJPoint<Camera2D> {
    private Target target;

    @Override
    public void render(float deltaTime, CanvasManager canvas) {
        super.render(deltaTime, canvas);

        if (target != null) {
            float smoothFactor = 1f - (float) Math.pow(1f - target.getSmooth(), deltaTime * 60f);

            Vec2 targetPoint = target.getPoint();
            Vec2 screenCenter = new Vec2(
                    VJade.getContext().getSize().x / (2f * getTarget().getZoom()),
                    VJade.getContext().getSize().y / (2f * getTarget().getZoom())
            );

            Vec2 desiredPosition = new Vec2(
                    targetPoint.x - screenCenter.x,
                    targetPoint.y - screenCenter.y
            );

            Vec2 currentPosition = getPosition();
            float newX = currentPosition.x + (desiredPosition.x - currentPosition.x) * smoothFactor;
            float newY = currentPosition.y + (desiredPosition.y - currentPosition.y) * smoothFactor;

            setPosition(newX, newY);

            canvas.setZoom(getTarget().getZoom());
        }

        canvas.setView(getPosition());
    }

    public Camera2D setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Target getTarget() {
        return target;
    }
}