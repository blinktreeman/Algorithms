package ru.bcomms.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;

    public void add(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>();
        node.setValue(value);
        if (root == null) {
            root = node;
        } else {
            BinaryTreeNode<T> currentNode = root;
            boolean nodeIsInPlace = false;
            do {
                if (node.getValue().compareTo(currentNode.getValue()) < 0) {
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode.setLeft(node);
                        nodeIsInPlace = true;
                    }
                } else {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        currentNode.setRight(node);
                        nodeIsInPlace = true;
                    }
                }
            } while (!nodeIsInPlace);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Empty tree";
        }
        StringBuilder sb = new StringBuilder();
        List<BinaryTreeNode<T>> nodes = new ArrayList<>();
        nodes.add(root);
        int tabs = treeHeight();
        sb.append("\t".repeat(Math.max(0, tabs--)));
        sb.append(root.getValue().toString()).append("\n");
        while (!nodes.isEmpty()) {
            List<BinaryTreeNode<T>> children = new ArrayList<>();
            sb.append("\t".repeat(Math.max(0, tabs--)));
            for (BinaryTreeNode<T> node : nodes) {
                if (node.getLeft() != null) {
                    children.add(node.getLeft());
                    sb.append(node.getLeft().getValue().toString()).append("\t");
                } else {
                    sb.append("n").append("\t");
                }
                if (node.getRight() != null) {
                    children.add(node.getRight());
                    sb.append(node.getRight().getValue().toString()).append("\t");
                } else {
                    sb.append("n").append("\t");
                }
            }
            sb.append("\n");
            nodes = children;
        }
        return sb.toString();
    }

    public int treeHeight() {
        return treeHeight(root);
    }

    private int treeHeight(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int heightLeft = treeHeight(node.getLeft());
        int heightRight = treeHeight(node.getRight());
        return heightLeft > heightRight ? heightLeft + 1 : heightRight + 1;
    }

}
