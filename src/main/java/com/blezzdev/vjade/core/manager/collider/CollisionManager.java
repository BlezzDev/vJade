package com.blezzdev.vjade.core.manager.collider;

import com.blezzdev.vjade.objects.collision.CircleCollider;
import com.blezzdev.vjade.objects.collision.Collider;
import com.blezzdev.vjade.objects.collision.RectangleCollider;
import com.blezzdev.vjade.tools.data.geometry.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CollisionManager implements ColliderAlgorithms {
    private final Map<String, Collider> colliderList = new HashMap<>();

    // Algorithms.

    @Override
    public boolean rectangleRectangle(RectangleCollider self, RectangleCollider other) {
        float selfMinX = self.getPosition().x - self.getSize().x / 2f;
        float selfMaxX = self.getPosition().x + self.getSize().x / 2f;
        float selfMinY = self.getPosition().y - self.getSize().y / 2f;
        float selfMaxY = self.getPosition().y + self.getSize().y / 2f;

        float otherMinX = other.getPosition().x - other.getSize().x / 2f;
        float otherMaxX = other.getPosition().x + other.getSize().x / 2f;
        float otherMinY = other.getPosition().y - other.getSize().y / 2f;
        float otherMaxY = other.getPosition().y + other.getSize().y / 2f;

        return selfMinX < otherMaxX &&
                selfMaxX > otherMinX &&
                selfMinY < otherMaxY &&
                selfMaxY > otherMinY;
    }

    @Override
    public boolean circleCircle(CircleCollider self, CircleCollider other) {
        Vector2 center1 = self.getPosition();
        Vector2 center2 = other.getPosition();

        float dx = center1.x - center2.x;
        float dy = center1.y - center2.y;
        float distanceSquared = dx * dx + dy * dy;

        float radiusSum = self.getRadius() + other.getRadius();

        return distanceSquared <= radiusSum * radiusSum;
    }

    @Override
    public boolean rectangleCircle(RectangleCollider rect, CircleCollider circle) {
        float closestX = Math.max(rect.getPosition().x - rect.getSize().x / 2f,
                Math.min(circle.getPosition().x, rect.getPosition().x + rect.getSize().x / 2f));
        float closestY = Math.max(rect.getPosition().y - rect.getSize().y / 2f,
                Math.min(circle.getPosition().y, rect.getPosition().y + rect.getSize().y / 2f));

        float dx = circle.getPosition().x - closestX;
        float dy = circle.getPosition().y - closestY;
        float distanceSquared = dx * dx + dy * dy;

        return distanceSquared <= circle.getRadius() * circle.getRadius();
    }


    // Manager functions.

    public String link(Collider collider) {
        String id = UUID.randomUUID().toString();
        colliderList.put(id, collider);
        return id;
    }

    public Map<String, Collider> getColliderList() {
        return colliderList;
    }

    public void unlink(String id) {
        colliderList.remove(id);
    }
}
