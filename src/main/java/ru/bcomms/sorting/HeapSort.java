package ru.bcomms.sorting;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void main(String[] args) {

        int[] array = generateArray(40000, 10000);
        System.out.println(Arrays.toString(array));

        long startTime = System.currentTimeMillis();
        heapSort(array, array.length - 1);
        long endTime = System.currentTimeMillis();

        System.out.println(Arrays.toString(array));
        System.out.printf("Completed in time: %d ms\n", endTime - startTime);

        /*
        Для массива 10000 элементов время сортировки:
        Completed in time: 79 ms
         */
    }

    public static void heapSort(int[] array, int toIndex) {
        // Условие выхода из рекурсии - для сортировки остался один элемент
        if (toIndex == 0) return;
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
        // Перемещаем максимум из позиции 0 в конец массива
        int temp = array[0];
        for (int i = 1; i <= toIndex; i++) {
            array[i - 1] = array[i];
        }
        array[toIndex] = temp;
        // Рекурсивно выполняем для toIndex - 1
        heapSort(array, toIndex - 1);
    }

    public static int[] generateArray(int arraySize, int bound) {
        int[] array = new int[arraySize];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(bound);
        }
        return array;
    }
}
