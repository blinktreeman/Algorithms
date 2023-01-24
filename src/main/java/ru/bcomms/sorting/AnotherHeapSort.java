package ru.bcomms.sorting;

import java.util.Arrays;
import java.util.Random;

public class AnotherHeapSort {

    public static void main(String[] args) {

        int[] array = generateArray(100000, 100000);
        System.out.println(Arrays.toString(array));

        long startTime = System.currentTimeMillis();
        heapSort(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();

        System.out.println(Arrays.toString(array));
        System.out.printf("Completed in time: %d ms\n", endTime - startTime);

        /*
        Для массива 10`000 элементов время сортировки:
        Completed in time: 51 ms
        Для массива 100`000 элементов время сортировки:
        Completed in time: 3473 ms
         */
    }

    public static void heapSort(int[] array, int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex; i++) {
            heapBuild(array, i, toIndex);
        }
    }

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

    public static int[] generateArray(int arraySize, int bound) {
        int[] array = new int[arraySize];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(bound);
        }
        return array;
    }
}
