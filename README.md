# Сортировка кучей

## Реализация 1. HeapSort

link: [src/main/java/ru/bcomms/sorting/HeapSort.java]()

1. Строим бинарное дерево

```java
// Построение кучи
for (int i = (toIndex - 1) / 2; i >= 0; i--) {
    if (array[i] < array[2 * i + 1]) {
        int temp = array[i];
        array[i] = array[2 * i + 1];
        array[2 * i + 1] = temp;
    }
    // Если у вершины есть второй лист
    if (2 * i + 2 <= toIndex && array[i] < array[2 * i + 2]) {
        int temp = array[i];
        array[i] = array[2 * i + 2];
        array[2 * i + 2] = temp;
    }
}
```

2. Пермещаем максимум с вершины в конец массива

```java
int temp = array[0];
for (int i = 1; i <= toIndex; i++) {
    array[i - 1] = array[i];
}
array[toIndex] = temp;
```

3. Рекурсивно выполняем для toIndex - 1

```java
heapSort(array, toIndex - 1);
```

Условие выхода из рекурсии

```java
if (toIndex == 0) return;
```

### Результат работы

Для массива 10'000 элементов время сортировки:  
Completed in time: 79 ms

> переполнение стека для размера массива > 30`000 элементов

## Реализация 2. AnotherHeapSort

link [src/main/java/ru/bcomms/sorting/AnotherHeapSort.java]()

1. Строим бинарное дерево перемещая наверх минимальное значение. 
При этом не переносим элемент с вершины, а строим следующее дерево начиная со 
следующего элемента в массиве. Таким образом выстраиваем элементы по возрастанию.

```java
public static void heapBuild(int[] array, int fromIndex, int toIndex) {
    for (int i = (fromIndex + toIndex - 1) / 2; i >= fromIndex; i--) {
        if (array[i] > array[2 * i + 1 - fromIndex]) {
            int temp = array[i];
            array[i] = array[2 * i + 1 - fromIndex];
            array[2 * i + 1 - fromIndex] = temp;
        }
        if (2 * i + 2 <= toIndex && array[i] > array[2 * i + 2 - fromIndex]) {
            int temp = array[i];
            array[i] = array[2 * i + 2 - fromIndex];
            array[2 * i + 2 - fromIndex] = temp;
        }
    }
}
```

2. Рекурсия заменена циклом

```java
public static void heapSort(int[] array, int fromIndex, int toIndex) {
    for (int i = fromIndex; i < toIndex; i++) {
        heapBuild(array, i, toIndex);
    }
}
```

### Результат работы

Для массива 10'000 элементов время сортировки:  
Completed in time: 51 ms  
Для массива 100'000 элементов время сортировки:  
Completed in time: 3473 ms  