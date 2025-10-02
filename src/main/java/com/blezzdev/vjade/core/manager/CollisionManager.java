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
        return selfCollider.getPosition().x < collider.getPosition().x + collider.getSize().x &&
                        selfCollider.getPosition().x + selfCollider.getSize().x > collider.getPosition().x &&
                        selfCollider.getPosition().y < collider.getPosition().y + collider.getSize().y &&
                        selfCollider.getPosition().y + selfCollider.getSize().y > collider.getPosition().y;
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
