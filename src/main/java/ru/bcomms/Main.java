package ru.bcomms;

import ru.bcomms.linked.MyDoublyLinkedList;
import ru.bcomms.linked.MyLinkedList;
import ru.bcomms.linked.MySinglyLinkedList;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MySinglyLinkedList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            linkedList.add(random.nextInt(100));
        }
        System.out.println(linkedList);
        linkedList.revert();
        System.out.println(linkedList);

        linkedList = new MyDoublyLinkedList<String>();
        linkedList.add("Hello");
        linkedList.add("world");
        linkedList.add("!");
        System.out.println(linkedList);
        linkedList.revert();
        System.out.println(linkedList);
    }

}