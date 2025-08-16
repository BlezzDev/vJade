package com.blezzdev.vjade.core.tree;

import com.blezzdev.vjade.elements.basic.Node;

public class TreeManager {
    private Node primaryNode;

    public void setPrimaryNode(Node primarySeed) {
        primaryNode = primarySeed;
    }

    public Node getPrimaryNode() {
        return primaryNode;
    }

    public void printTree() {
        printTreeProcess(primaryNode, 0);
    }

    private void printTreeProcess(Node node, int column) {

        // Print the shape of the root.

        int localColumn = column;
        while (localColumn > 0) {
            if (localColumn == column) { System.out.print("-> "); }
            else if (localColumn == 1) { System.out.print("  |-"); }
            else { System.out.print("---"); }

            localColumn--;
        }

        // Print the corresponding names of each node of the root.

        for (int i = 0; i < node.getChildrens().size(); i++) {
            Node child = node.getChildren(i);
            System.out.println(child.getName());
            if (!child.getChildrens().isEmpty()) {
                printTreeProcess(child, column + 1);
            }
        }
    }
}
