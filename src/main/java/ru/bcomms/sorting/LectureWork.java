package ru.bcomms.sorting;

import java.util.Arrays;
import java.util.Random;

public class LectureWork {
    public static void main(String[] args) {

        int[] array = generateArray(100000, 100000);
        System.out.println(Arrays.toString(array));

        long startTime = System.currentTimeMillis();
        /*
        Пузырьковая сортировка для arraySize = 100000, bound = 100000
        Выполнена за: Completed in time: 18717 ms
         */
        // int[] sortedArray = bubbleSort(array);

        /*
        Сортировка выбором для arraySize = 100000, bound = 100000
        Выполнено за: Completed in time: 12372 ms
         */
        // int[] sortedArray = directSort(array);

        /*
        Сортировка вставками для arraySize = 100000, bound = 100000
        Выполнено за: Completed in time: 17492 ms
         */
        //int[] sortedArray = insertSort(array);

        int[] sortedArray = quickSort(array, 0, array.length-1);
        long endTime  = System.currentTimeMillis();

        System.out.println(Arrays.toString(sortedArray));
        System.out.printf("Completed in time: %d ms\n", endTime - startTime);

        /*
        Поиск
         */

        startTime = System.currentTimeMillis();
        int value = 99996;
        /*
        Простой поиск
        Completed in time: 2 ms
         */
        // int index = simpleSearch(array, value);

        /*
        Бинарный поиск
        Completed in time: 0 ms
         */
        int index = binarySearch(array, value, 0, sortedArray.length-1);
        endTime  = System.currentTimeMillis();
//        if (index != -1) {
//            System.out.printf("Index of element with value %d is %d\n", value, index);
//            System.out.printf("Completed in time: %d ms\n", endTime - startTime);
//        }
//        else {
//            System.out.println("Value not found");
//        }
    }

    public static int[] generateArray(int arraySize, int bound) {
        int[] array = new int[arraySize];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(bound);
        }
        return array;
    }

    public static int[] bubbleSort(int[] array) {
        boolean sorted = true;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
        return array;
    }

    public static int[] directSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min_index]) {
                    min_index = j;
                }
            }
            if (min_index != i) {
                int temp = array[min_index];
                array[min_index] = array[i];
                array[i] = temp;
            }
        }
        return array;
    }

    public static int[] insertSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static int[] quickSort(int[] array, int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            return array;
        }
        else {
            // Выбираем базовый элемент
            int baseIndex = (fromIndex + toIndex) / 2;
            // Для всех элементов слева от базового
            for (int i = fromIndex; i < baseIndex; i++) {
                // Если значение больше базового
                if (array[i] >= array[baseIndex]) {
                    boolean replaced = false;
                    // Ищем в правой части элемент со значением меньше базового
                    for (int j = toIndex; j >= baseIndex; j--) {
                        if (array[j] < array[baseIndex]) {
                            // Если найден, меняем их местами
                            int temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                            replaced = true;
                            break;
                        }
                    }
                    // Если замену справа найти не удалось, сдвигаем все включая baseIndex влево
                    // текущий элемент на его место
                    if (!replaced) {
                        int temp = array[i];
                        for (int j = i; j < baseIndex; j++) {
                            array[j] = array[j + 1];
                        }
                        array[baseIndex] = temp;
                        // Базовый элемент изменил положение
                        baseIndex--;
                    }
                }
            }
            // Проверяем не осталось ли элементов меньше базового справа от него
            for (int i = toIndex; i >= baseIndex; i--) {
                // Если есть, сдвигаем все вправо, найденный элемент ставим слева от baseIndex
                if (array[i] <= array[baseIndex]) {
                    int temp = array[i];
                    for (int j = i; j > baseIndex; j--) {
                        array[j] = array[j - 1];
                    }
                    array[baseIndex] = temp;
                    // Базовый элемент изменил положение
                    baseIndex++;
                }
            }
            System.out.println(baseIndex);
            // Разбиваем на две части по базовому индексу, рекурсивно повторияем для обеих частей
            //quickSort(array, fromIndex, baseIndex - 1);
            //quickSort(array, baseIndex + 1, toIndex);
        }
        return array;
    }

    // Search algorithms
    public static int simpleSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] array, int value, int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            if (array[fromIndex] == value) {
                return fromIndex;
            }
            else {
                return -1;
            }
        }
        else {
            if (array[(toIndex + fromIndex) / 2] >= value) {
                return binarySearch(array, value, fromIndex, (toIndex + fromIndex) / 2);
            }
            else {
                return binarySearch(array, value, (int) Math.ceil(((float)toIndex + (float) fromIndex) / 2), toIndex);
            }
        }
    }
}
