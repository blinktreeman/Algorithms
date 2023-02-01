# Красно-черное бинарное дерево

## Нода дерева [RBBinaryTreeNode.java](RBBinaryTreeNode.java)

```java
package ru.bcomms.tree;

public class RBBinaryTreeNode<T extends Comparable<T>> {
    private T value;
    private RBBinaryTreeNode<T> left;
    private RBBinaryTreeNode<T> right;
    private NodeColor color;

    /*
            Getters and setters
     */
}
```

## Реализация дерева [RBBinaryTree.java](RBBinaryTree.java)

### Методы левого/правого поворота

```java
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
```

### Методы смены цвета

```java
// Смена цвета
private void colorChange(RBBinaryTreeNode<T> node) {
    node.getLeft().setColor(NodeColor.BLACK);
    node.getRight().setColor(NodeColor.BLACK);
    node.setColor(NodeColor.RED);
}
```

### Метод балансировки ветвей

```java
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
                && node.getRight() != null 
                && node.getRight().getColor().equals(NodeColor.RED)) {
            colorChange(node);
            needBalancing = true;
        }
        // Если корень стал красным – просто перекрашиваем его в черный
        if (node == root && node.getColor().equals(NodeColor.RED)) {
            node.setColor(NodeColor.BLACK);
        }
    } while (needBalancing);
    return node;
}
```

### Метод добавления значения

```java
public void add(T value) {
    // Если root-а нет
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
```

## Результат работы

### Вывод для несбалансированного бинарного дерева

При добавлении в дерево из main-метода:

```java
BinaryTree<Integer> binaryTree = new BinaryTree<>();
for (int i = 10; i > 0; i--) {
    binaryTree.add(i);
}
System.out.println(binaryTree);
```

Вывод:

```shell
|-- 10
    |-- 9
        |-- 8
            |-- 7
                |-- 6
                    |-- 5
                        |-- 4
                            |-- 3
                                |-- 2
                                    |-- 1
```

### Вывод для красно-черного бинарного дерева

При добавлении в дерево из main-метода:

```java
RBBinaryTree<Integer> RBBinaryTree = new RBBinaryTree<>();
for (int i = 1; i < 15; i++) {
    RBBinaryTree.add(i);
}
System.out.println(RBBinaryTree);
```

Вывод:

```shell
          |-- 14 BLACK
               |-- 13 RED
     |-- 12 BLACK
               |-- 11 BLACK
          |-- 10 RED
               |-- 9 BLACK
|-- 8 BLACK
               |-- 7 BLACK
          |-- 6 BLACK
               |-- 5 BLACK
     |-- 4 RED
               |-- 3 BLACK
          |-- 2 BLACK
               |-- 1 BLACK
```