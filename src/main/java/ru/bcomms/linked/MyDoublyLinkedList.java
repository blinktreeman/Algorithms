package ru.bcomms.linked;

import ru.bcomms.linked.nodes.DoublyLinkedNode;

public class MyDoublyLinkedList<T> implements MyLinkedList<DoublyLinkedNode<T>, T> {
    private DoublyLinkedNode<T> head;
    private DoublyLinkedNode<T> tail;
    @Override
    public boolean add(T value) {
        DoublyLinkedNode<T> node = new DoublyLinkedNode<T>();
        node.setValue(value);
        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        return true;
    }

    @Override
    public boolean delete(DoublyLinkedNode<T> node) {
        if (node.getPrevious() == null) {
            node.getNext().setPrevious(null);
            head = node.getNext();
        }
        else {
            if (node.getNext() == null) {
                node.getPrevious().setNext(null);
                tail = node.getPrevious();
            }
            else {
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
            }
        }
        return true;
    }

    @Override
    public DoublyLinkedNode<T> find(T value) {
        DoublyLinkedNode<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.getValue() == value) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public boolean revert() {
        if (head == null || head.getNext() == null) {
            return false;
        }
        DoublyLinkedNode<T> currentNode = head;
        while (currentNode != null) {
            DoublyLinkedNode<T> next = currentNode.getNext();
            DoublyLinkedNode<T> previous = currentNode.getPrevious();
            currentNode.setNext(previous);
            currentNode.setPrevious(next);
            if (previous == null) {
                tail = currentNode;
            }
            if (next == null) {
                head = currentNode;
            }
            currentNode = next;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoublyLinkedNode<T> node = head;
        while (node != null) {
            sb.append(node.getValue().toString()).append(" ");
            node = node.getNext();
        }
        return sb.toString();
    }
}
