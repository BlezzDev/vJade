package com.blezzdev.vjade.core.manager;

import com.blezzdev.vjade.objects.collision.Collider;
import com.blezzdev.vjade.objects.processes.ColliderAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager implements ColliderAlgorithms {
    private final List<Collider> colliderList = new ArrayList<>();

    // Algorithms.

    @Override
    public boolean rectangleRectangle(Collider selfCollider, Collider collider) {
        float selfMinX = selfCollider.getPosition().x - selfCollider.getSize().x / 2f;
        float selfMaxX = selfCollider.getPosition().x + selfCollider.getSize().x / 2f;
        float selfMinY = selfCollider.getPosition().y - selfCollider.getSize().y / 2f;
        float selfMaxY = selfCollider.getPosition().y + selfCollider.getSize().y / 2f;

        float otherMinX = collider.getPosition().x - collider.getSize().x / 2f;
        float otherMaxX = collider.getPosition().x + collider.getSize().x / 2f;
        float otherMinY = collider.getPosition().y - collider.getSize().y / 2f;
        float otherMaxY = collider.getPosition().y + collider.getSize().y / 2f;

        return selfMinX < otherMaxX &&
                selfMaxX > otherMinX &&
                selfMinY < otherMaxY &&
                selfMaxY > otherMinY;
    }


    // Manager functions.

    public int link(Collider collider) {
        colliderList.add(collider);
        return colliderList.size() - 1;
    }

    public List<Collider> getColliderList() {
        return colliderList;
    }

    public void unlink(int id) {
        colliderList.remove(id);
    }
}
