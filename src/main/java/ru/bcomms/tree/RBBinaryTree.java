package ru.bcomms.tree;

public class RBBinaryTree<T extends Comparable<T>> {
    private RBBinaryTreeNode<T> root;
    private final StringBuilder treeVisualization = new StringBuilder();

    public void add(T value) {
        RBBinaryTreeNode<T> node = new RBBinaryTreeNode<>();
        node.setValue(value);
        if (root == null) {
            root = node;
        } else {
            RBBinaryTreeNode<T> currentNode = root;
            do {
                if (node.getValue().compareTo(currentNode.getValue()) < 0) {
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode.setLeft(node);
                        break;
                    }
                } else {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        currentNode.setRight(node);
                        break;
                    }
                }
            } while (true);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Empty tree";
        }
        buildTreeVisualization("", root);
        return treeVisualization.toString();
    }

    public void buildTreeVisualization(String prefix, RBBinaryTreeNode<T> node) {
        if (node != null) {
            buildTreeVisualization(prefix + "    ", node.getRight());
            treeVisualization.append(prefix).append("|-- ").append(node.getValue()).append("\n");
            buildTreeVisualization(prefix + "    ", node.getLeft());
        }
    }

}
