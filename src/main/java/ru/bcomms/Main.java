package ru.bcomms;

import ru.bcomms.tree.BinaryTree;
import ru.bcomms.tree.RBBinaryTree;

public class Main {
    public static void main(String[] args) {
        // Несбалансированное бинарное дерево
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for (int i = 10; i > 0; i--) {
            binaryTree.add(i);
        }
        System.out.println(binaryTree);
        /*
        Output:
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
         */

        RBBinaryTree<Integer> RBBinaryTree = new RBBinaryTree<>();
        for (int i = 10; i > 0; i--) {
            RBBinaryTree.add(i);
        }
        System.out.println(RBBinaryTree);
    }

}