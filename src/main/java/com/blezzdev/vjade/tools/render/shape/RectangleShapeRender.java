package com.blezzdev.vjade.tools.render.shape;

import com.blezzdev.vjade.tools.Vector2;
import com.blezzdev.vjade.tools.color.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class RectangleShapeRender extends ShapeRender {
    private Vector2 size;
    private Vector2 position;

    public RectangleShapeRender(Vector2 position, Vector2 size, Color color) {
        super(color);

        this.size = size;
        this.position = position;
    }

    @Override
    public void render() {
        glColor3f(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
        glBegin(GL_QUADS);

        glVertex2f(position.getIntX() + size.getIntX(), position.getIntY());
        glVertex2f(position.getIntX() + size.getIntX(), position.getIntY() + size.getIntY());
        glVertex2f(position.getIntX(), position.getIntY() + size.getIntY());
        glVertex2f(position.getIntX(), position.getIntY());

        glEnd();
    }
}
