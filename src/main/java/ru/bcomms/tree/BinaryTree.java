package ru.bcomms.tree;

public class BinaryTree<T extends Comparable<T>> {
    private BinaryTreeNode<T> root;
    private final StringBuilder treeVisualization = new StringBuilder();

    /**
     * Добавить значение
     * @param value Значение
     */
    public void add(T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>();
        node.setValue(value);
        if (root == null) {
            root = node;
        } else {
            BinaryTreeNode<T> currentNode = root;
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

//        List<BinaryTreeNode<T>> nodes = new ArrayList<>();
//        nodes.add(root);
//        while (!nodes.isEmpty()) {
//            List<BinaryTreeNode<T>> children = new ArrayList<>();
//            for (BinaryTreeNode<T> node : nodes) {
//                if (node.getLeft() != null) {
//                    children.add(node.getLeft());
//                }
//                if (node.getRight() != null) {
//                    children.add(node.getRight());
//                }
//            }
//            nodes = children;
//        }

    }

    public void buildTreeVisualization(String prefix, BinaryTreeNode<T> node) {
        if (node != null) {
            buildTreeVisualization(prefix + "    ", node.getRight());
            treeVisualization.append(prefix).append("|-- ").append(node.getValue()).append("\n");
            buildTreeVisualization(prefix + "    ", node.getLeft());
        }
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
