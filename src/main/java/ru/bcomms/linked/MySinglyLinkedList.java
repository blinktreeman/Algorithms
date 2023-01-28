package ru.bcomms.linked;

import ru.bcomms.linked.nodes.SinglyLinkedNode;

public class MySinglyLinkedList<T> implements MyLinkedList<SinglyLinkedNode<T>, T> {
    private SinglyLinkedNode<T> head;
    @Override
    public boolean add(T value) {
        SinglyLinkedNode<T> node = new SinglyLinkedNode<>();
        node.setValue(value);
        if (head == null) {
            head = node;
        }
        else {
            SinglyLinkedNode<T> currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(node);
        }
        return true;
    }

    @Override
    public boolean delete(SinglyLinkedNode<T> node) {
        if (head == null) {
            return false;
        }
        SinglyLinkedNode<T> currentNode = head;
        while (currentNode.getNext() != null) {
            if (currentNode.getNext() == node) {
                if (currentNode.getNext().getNext() != null) {
                    currentNode.setNext(currentNode.getNext().getNext());
                }
                else {
                    currentNode.setNext(null);
                }
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public SinglyLinkedNode<T> find(T value) {
        if (head == null) {
            return null;
        }
        SinglyLinkedNode<T> node = head;
        while (node.getNext() != null) {
            if (node.getValue().equals(value)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public boolean revert() {
        if (head != null && head.getNext() != null) {
            return revert(head, head.getNext());
        }
        return false;
    }

    private boolean revert(SinglyLinkedNode<T> current, SinglyLinkedNode<T> next) {
        if (next.getNext() == null) {
            head = next;
        }
        else {
            revert(next, next.getNext());
        }
        next.setNext(current);
        current.setNext(null);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SinglyLinkedNode<T> node = head;
        while (node != null) {
            sb.append(node.getValue().toString()).append(" ");
            node = node.getNext();
        }
        return sb.toString();
    }
}
