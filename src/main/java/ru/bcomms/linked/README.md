# Реверс списка

## Реверс односвязного списка

Реверс односвязного списка реализован в методе `revert` класса 
[MySinglyLinkedList.java](MySinglyLinkedList.java)

```java
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
```

## Реверс двусвязного списка

Реверс двусвязного списка реализован в методе `revert` класса 
[MyDoublyLinkedList.java](MyDoublyLinkedList.java)

```java
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
```

## Результат работы `main` метода

### Main метод

```java
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
```
### Output:

```shell
59 39 76 28 28 51 63 80 23 84 
84 23 80 63 51 28 28 76 39 59 

Hello world ! 
! world Hello 
```