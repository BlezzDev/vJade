package com.blezzdev.vjade.elements.basic.node;

import com.blezzdev.vjade.core.window.tree.TreeManager;

import java.util.ArrayList;
import java.util.List;

public class Node implements NodeStructure {
    private TreeManager seed; // Pointer to the tree seed

    private Node parent;
    private List<Node> childrens = new ArrayList<>();
    private String name = "Node";

    public Node() {}
    public Node(TreeManager primary) {
        seed = primary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setParent(Node parent) {
        // The node identifies its parent, if it already has a relative,
        // it removes its identity from the old parent and adds itself
        // as a child node.

        if (!(this.parent == null)) {
            this.parent.removeChildren(this);
        }

        this.parent = parent;
        if (parent != null) { parent.addChildren(this); }
    }

    private void addChildren(Node node) { childrens.add(node); }
    private void removeChildren(Node node) { childrens.remove(node); }

    public List<Node> getChildrens() { return childrens; }
    public Node getChildren(int index) { return childrens.get(index); }
    public Node getParent() { return parent; }
    public TreeManager getSeed() { return seed; }
    public String getName() { return name; }
}
