package com.blezzdev.vjade.core.manager.canvas.batch;

import com.blezzdev.vjade.tools.data.color.Color;
import com.blezzdev.vjade.tools.data.geometry.specialized.Geometry;
import com.blezzdev.vjade.tools.data.geometry.specialized.Pivot;
import com.blezzdev.vjade.tools.data.geometry.Vec2;
import com.blezzdev.vjade.tools.data.geometry.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3f;

class RenderCalculator {
    private final Matrix4f transform = new Matrix4f();
    private final Vector3f worldVertex = new Vector3f();

    void buildGeometry(Vec3 position, Vec2 size, Pivot pivot, float[] uvs, Color color, float rotation, boolean hFlip, float zoom, Vec2 view, Geometry geom) {
        transform.identity()
                .translate(position.x, position.y, position.z)
                .translate(pivot.getX() * size.x, pivot.getY() * size.y, 0)
                .rotateZ((float) -Math.toRadians(rotation))
                .translate(-pivot.getX() * size.x, -pivot.getY() * size.y, 0);

        if (zoom != 0) transform.scale(zoom, zoom, 1);
        if (view != null) transform.translate(-view.x, -view.y, 0);

        float halfW = size.x * 0.5f;
        float halfH = size.y * 0.5f;

        if (hFlip) {
            transformVertex(-halfW, -halfH, uvs[2], uvs[1], geom, color, transform);
            transformVertex( halfW, -halfH, uvs[0], uvs[1], geom, color, transform);
            transformVertex( halfW,  halfH, uvs[0], uvs[3], geom, color, transform);
            transformVertex(-halfW,  halfH, uvs[2], uvs[3], geom, color, transform);
        } else {
            transformVertex(-halfW, -halfH, uvs[0], uvs[1], geom, color, transform);
            transformVertex( halfW, -halfH, uvs[2], uvs[1], geom, color, transform);
            transformVertex( halfW,  halfH, uvs[2], uvs[3], geom, color, transform);
            transformVertex(-halfW,  halfH, uvs[0], uvs[3], geom, color, transform);
        }

    }

    private void transformVertex(float x, float y, float u, float v,
                                 Geometry geom, Color color, Matrix4f transform) {
        worldVertex.set(x, y, 0);
        transform.transformPosition(worldVertex);

        geom.newVertex(worldVertex.x, worldVertex.y, worldVertex.z,
                u, v,
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    void calculateUVs(int frame, int hDivs, int vDivs, float[] result) {
        float spriteWidth = 1.0f / hDivs;
        float spriteHeight = 1.0f / vDivs;

        int column = frame % hDivs;
        int row = frame / hDivs;

        row %= vDivs;

        float u0 = column * spriteWidth;
        float v0 = row * spriteHeight;
        float u1 = u0 + spriteWidth;
        float v1 = v0 + spriteHeight;

        result[0] = u0;
        result[1] = v0;
        result[2] = u1;
        result[3] = v1;
    }
}