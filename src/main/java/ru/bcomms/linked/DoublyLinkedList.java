package ru.bcomms.linked;

public class DoublyLinkedList <T> {
    Node<T> head;
    Node<T> tail;

    public void add(T value) {
        Node<T> node = new Node<T>();
        node.value = value;
        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }

    public void add(T value, Node<T> node) {
        Node<T> next = node.next;
        Node<T> newNode = new Node<T>();
        newNode.value = value;
        newNode.previous = node;
        if (next == null) {
            tail = newNode;
        }
        else {
            next.previous = newNode;
            newNode.next = next;
        }
    }

    public void delete(Node<T> node) {
        if (node.previous == null) {
            node.next.previous = null;
            head = node.next;
        }
        else {
            if (node.next == null) {
                node.previous.next = null;
                tail = node.previous;
            }
            else {
                node.previous.next = node.next;
                node.next.previous = node.previous;
            }
        }
    }

    public Node<T> find(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.value == value) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public void revert() {
        Node<T> currentNode = head;
        while (currentNode != null) {
            Node<T> next = currentNode.next;
            Node<T> previous = currentNode.previous;
            currentNode.next = previous;
            currentNode.previous = next;
            if (previous == null) {
                tail = currentNode;
            }
            if (next == null) {
                head = currentNode;
            }
            currentNode = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> node = head;
        while (node != null) {
            sb.append(node.value.toString()).append(" ");
            node = node.next;
        }
        return sb.toString();
    }

    protected class Node <T> {
        T value;
        Node<T> next;
        Node<T> previous;
    }
}
