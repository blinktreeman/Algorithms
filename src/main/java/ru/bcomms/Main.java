package ru.bcomms;

import ru.bcomms.linked.DoublyLinkedList;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        System.out.println(linkedList);
        linkedList.revert();
        System.out.println(linkedList);

        DoublyLinkedList<String> stringDoublyLinkedList = new DoublyLinkedList<>();
        stringDoublyLinkedList.add("Hello");
        stringDoublyLinkedList.add("world");
        stringDoublyLinkedList.add("!");
        System.out.println(stringDoublyLinkedList);
        stringDoublyLinkedList.revert();
        System.out.println(stringDoublyLinkedList);
    }

}