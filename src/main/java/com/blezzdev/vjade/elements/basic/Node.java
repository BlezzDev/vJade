package com.blezzdev.vjade.elements.basic;

import com.blezzdev.vjade.core.tree.TreeManager;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private TreeManager seed; // Pointer to the tree seed

    private Node parent;
    private List<Node> childrens = new ArrayList<>();
    private String name;

    public Node() {}
    public Node(TreeManager primary) {
        primary.setPrimaryNode(this);
        seed = primary;
    }

    public Node(Node parent) {

        // If a node is added with a preset relative, the seed is collected
        // taking into account the seed of the parent node and added
        // as a child node.

        this.seed = parent.getSeed();

        setParent(parent);
    }

    public void setName(String name) { this.name = name; }
    public void setParent(Node parent) {

        // The node identifies its parent, if it already has a relative,
        // it removes its identity from the old parent and adds itself
        // as a child node.

        this.parent = parent;
        if (!parent.childrens.isEmpty()) {
            this.parent.removeChildren(this);
        }
        parent.addChildren(this);
    }

    public List<Node> getChildrens() { return childrens; }
    public Node getChildren(int index) { return childrens.get(index); }
    public Node getParent() { return parent; }
    public TreeManager getSeed() { return seed; }
    public String getName() { return name; }
    public void addChildren(Node node) { childrens.add(node); }
    public void removeChildren(Node node) { childrens.remove(node); }
    public void removeChildren(int index) { childrens.remove(index); }

    // Node birth, rendering, update, and death methods.

    public void start() {}
    public void render() {}
    public void update() {}
    public void end() {}
}
