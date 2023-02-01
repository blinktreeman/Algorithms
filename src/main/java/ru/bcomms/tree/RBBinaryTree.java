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

    // Левый малый поворот
    private RBBinaryTreeNode<T> leftTurn(RBBinaryTreeNode<T> node) {
        RBBinaryTreeNode<T> leftChild = node.getLeft();
        RBBinaryTreeNode<T> betweenParentAndChild = node.getLeft().getRight();
        node.setLeft(betweenParentAndChild);
        leftChild.setRight(node);
        leftChild.setColor(node.getColor());
        node.setColor(NodeColor.RED);
        return leftChild;
    }

    // Правый малый поворот
    private RBBinaryTreeNode<T> rightTurn(RBBinaryTreeNode<T> node) {
        RBBinaryTreeNode<T> rightChild = node.getRight();
        RBBinaryTreeNode<T> betweenParentAndChild = node.getRight().getLeft();
        node.setRight(betweenParentAndChild);
        rightChild.setLeft(node);
        rightChild.setColor(node.getColor());
        node.setColor(NodeColor.RED);
        return rightChild;
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
