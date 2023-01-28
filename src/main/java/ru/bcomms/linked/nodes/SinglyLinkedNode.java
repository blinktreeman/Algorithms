package ru.bcomms.linked.nodes;

public class SinglyLinkedNode <T> {
    private SinglyLinkedNode<T> next;
    private T value;

    public SinglyLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(SinglyLinkedNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
