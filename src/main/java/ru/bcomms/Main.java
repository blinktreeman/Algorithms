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
        for (int i = 1; i < 15; i++) {
            RBBinaryTree.add(i);
        }
        System.out.println(RBBinaryTree);
        /*
        Output:
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
         */
    }

}