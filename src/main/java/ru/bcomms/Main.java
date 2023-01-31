package ru.bcomms;

import ru.bcomms.tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(10);
        binaryTree.add(1);
        binaryTree.add(20);
        binaryTree.add(5);
        binaryTree.add(3);
        binaryTree.add(15);
        binaryTree.add(22);

        System.out.println(binaryTree);
        System.out.println(-11 % 2);
    }

}