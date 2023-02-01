package ru.bcomms.tree;

public class RBBinaryTree<T extends Comparable<T>> {
    private RBBinaryTreeNode<T> root;
    private final StringBuilder treeVisualization = new StringBuilder();

    public void add(T value) {
        if (root == null) {
            root = new RBBinaryTreeNode<>();
            root.setValue(value);
            root.setColor(NodeColor.BLACK);
        } else {
            add(root, value);
            root = treeBalancing(root);
        }
    }

    private void add(RBBinaryTreeNode<T> node, T value) {
        if (node.getValue().compareTo(value) > 0) {
            // Если нода не пуста
            if (node.getLeft() != null) {
                // Рекурсивно идем в глубину
                add(node.getLeft(), value);
                // При каждом выходе из рекурсии балансируем дерево
                // от нижней ноды к root-у
                node.setLeft(treeBalancing(node.getLeft()));
            } else {
                // Иначе размещаем ноду на пустом месте
                node.setLeft(new RBBinaryTreeNode<>());
                node.getLeft().setValue(value);
                node.getLeft().setColor(NodeColor.RED);
            }
        } else {
            if (node.getRight() != null) {
                add(node.getRight(), value);
                node.setRight(treeBalancing(node.getRight()));
            } else {
                node.setRight(new RBBinaryTreeNode<>());
                node.getRight().setValue(value);
                node.getRight().setColor(NodeColor.RED);
            }
        }
    }

    /*
    Правила балансировки
    Если правый ребенок – красный, а левый - черный, то применяем малый правый поворот
	Если левый ребенок красный и его левый ребенок тоже красный – применяем малый левый поворот
	Если оба ребенка красные – делаем смену цвета
	Если корень стал красным – просто перекрашиваем его в черный
     */
    private RBBinaryTreeNode<T> treeBalancing(RBBinaryTreeNode<T> node) {
        boolean needBalancing;
        do {
            needBalancing = false;
            // Если правый ребенок – красный,
            if (node.getRight() != null && node.getRight().getColor().equals(NodeColor.RED)
                    // а левый - черный, то применяем малый правый поворот
                    && (node.getLeft() == null || node.getLeft().getColor().equals(NodeColor.BLACK))) {
                node = rightTurn(node);
                // Нужна проверка, возможна дальнейшая балансировка
                needBalancing = true;
            }
            // Если левый ребенок красный
            if (node.getLeft() != null && node.getLeft().getColor().equals(NodeColor.RED)
                    // и его левый ребенок тоже красный – применяем малый левый поворот
                    && node.getLeft().getLeft() != null
                    && node.getLeft().getLeft().getColor().equals(NodeColor.RED)) {
                node = leftTurn(node);
                needBalancing = true;
            }
            // Если оба ребенка красные – делаем смену цвета
            if (node.getLeft() != null && node.getLeft().getColor().equals(NodeColor.RED)
                    && node.getRight() != null && node.getRight().getColor().equals(NodeColor.RED)) {
                colorChange(node);
                needBalancing = true;
            }
            // Если корень стал красным – просто перекрашиваем его в черный
            if (node == root && node.getColor().equals(NodeColor.RED)) {
                node.setColor(NodeColor.BLACK);
                //needBalancing = true;
            }
        } while (needBalancing);
        return node;
    }

    // Левый малый поворот
    private RBBinaryTreeNode<T> leftTurn(RBBinaryTreeNode<T> node) {
        RBBinaryTreeNode<T> leftChild = node.getLeft();
        RBBinaryTreeNode<T> betweenParentAndChild = node.getLeft().getRight();
        leftChild.setRight(node);
        leftChild.setColor(node.getColor());
        node.setLeft(betweenParentAndChild);
        node.setColor(NodeColor.RED);
        return leftChild;
    }

    // Правый малый поворот
    private RBBinaryTreeNode<T> rightTurn(RBBinaryTreeNode<T> node) {
        RBBinaryTreeNode<T> rightChild = node.getRight();
        RBBinaryTreeNode<T> betweenParentAndChild = node.getRight().getLeft();
        rightChild.setLeft(node);
        rightChild.setColor(node.getColor());
        node.setRight(betweenParentAndChild);
        node.setColor(NodeColor.RED);
        return rightChild;
    }

    // Смена цвета
    private void colorChange(RBBinaryTreeNode<T> node) {
        node.getLeft().setColor(NodeColor.BLACK);
        node.getRight().setColor(NodeColor.BLACK);
        node.setColor(NodeColor.RED);
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
            buildTreeVisualization(prefix + "     ", node.getRight());
            treeVisualization.append(prefix)
                    .append("|-- ")
                    .append(node.getValue())
                    .append(" ")
                    .append(node.getColor().toString())
                    .append("\n");
            buildTreeVisualization(prefix + "     ", node.getLeft());
        }
    }

}
