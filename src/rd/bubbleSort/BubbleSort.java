package rd.bubbleSort;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {

    public static void main(String[] args) {
        testInt(5, 0, 1000);
    }

    static int[] generateArray(int length, int valuesFrom, int valuesTo) {
        int[] array = new int[length];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(valuesFrom, valuesTo);
        }
        return array;
    }

    static void displayArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    static void bubbleSort(int[] array) {
        int buffer;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    buffer = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = buffer;
                }
            }
        }
    }

    /**
     * Break if no changes to array were made
     */
    static void bubbleSortOpmimize1(int[] array) {
        int buffer;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    buffer = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = buffer;
                    changed = true;
                }
            }
        }
    }

    /**
     * Loop only up to last sorted number (after every iteration the biggest number
     * is moved to the end and we dont need to compare it in next iterations)
     */
    static void bubbleSortOpmimize2(int[] array) {
        int buffer;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    buffer = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = buffer;
                }
            }
        }
    }

    /**
     * Optimization #1: Break if no changes to array were made
     * 
     * Optimization #2: Loop only up to last sorted number (after every iteration
     * the biggest number is moved to the end and we dont need to compare it in next
     * iterations)
     */
    static void bubbleSortOpmimize1x2(int[] array) {
        int buffer;
        boolean changed = true;
        for (int i = 0; i < array.length; i++) {
            changed = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    buffer = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = buffer;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
    }

    static void testInt(int length, int valuesFrom, int valuesTo) {
        int[] intArray = generateArray(length, valuesFrom, valuesTo);
        long startTime, endTime;
        int[] testArray;

        int[] arrayLengthsTestValues = { 1000, 10000, 50000, 60000, 70000, 80000, 90000, 100000, 110000, 120000, 130000,
                140000, 150000, 160000, 170000 };
        String[] testNames = { "Bubble Sort (BS)", "BS Opt #1", "BS Opt #2", "BS Opt #1 & #2" };
        for (int i = 0; i < arrayLengthsTestValues.length; i++) {
            for (int j = 0; j < testNames.length; j++) {
                if (i == 0 && j > 0) {
                    System.out.print(testNames[j] + "\t");
                }
            }
        }
        testArray = Arrays.copyOf(intArray, intArray.length);
        startTime = System.currentTimeMillis();
        bubbleSortOpmimize1x2(testArray);
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

}
