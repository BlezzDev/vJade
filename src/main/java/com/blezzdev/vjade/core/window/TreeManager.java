package com.blezzdev.vjade.core.window;

import com.blezzdev.vjade.elements.build.Node;
import com.blezzdev.vjade.elements.build.Root;

public class TreeManager {
    private Root primaryNode;

    public void setPrimaryNode(Root primarySeed) {
        this.primaryNode = primarySeed;
    }

    public Root getPrimaryNode() {
        return primaryNode;
    }

    public void printTree() {
        System.out.println("Root (Root)");
        printTreeProcess(primaryNode, 1);
    }

    private void printTreeProcess(Node node, int column) {
        if (node.getChildrens().isEmpty()) { return; }

        // Print the shape of the root.

        int localColumn = column;
        while (localColumn > 0) {
            System.out.print(" > ");
            localColumn--;
        }

        // Print the corresponding names of each node of the root.

        for (int i = 0; i < node.getChildrens().size(); i++) {
            Node child = node.getChildren(i);

            System.out.println(child.getName() + " (" + child.getClass().getSimpleName() + ")");
            if (!child.getChildrens().isEmpty()) {
                printTreeProcess(child, column + 1);
            }
        }
    }
}
