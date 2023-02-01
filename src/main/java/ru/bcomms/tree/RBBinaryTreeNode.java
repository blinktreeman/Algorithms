package ru.bcomms.tree;

public class RBBinaryTreeNode<T extends Comparable<T>> {
    private T value;
    private RBBinaryTreeNode<T> left;
    private RBBinaryTreeNode<T> right;
    private NodeColor color;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public RBBinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBBinaryTreeNode<T> left) {
        this.left = left;
    }

    public RBBinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RBBinaryTreeNode<T> right) {
        this.right = right;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }
}
