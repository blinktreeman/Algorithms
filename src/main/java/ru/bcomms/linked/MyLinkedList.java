package ru.bcomms.linked;

public interface MyLinkedList <N, T>{
    boolean add(T value);
    boolean delete(N node);
    N find(T value);
    boolean revert();
}
