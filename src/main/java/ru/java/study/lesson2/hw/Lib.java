package ru.java.study.lesson2.hw;

import java.util.Arrays;
import java.util.Random;

public class Lib {
    /**
     * @param arraySize num of elements
     * @param min min element value (including)
     * @param max max element value (including)
     * @return Generated array
     */
    public static int[] generateNumbersArray(int arraySize, int min, int max) {
        Random rand = new Random();
        int[] result = new int[arraySize];
        for (int i = 0; i < arraySize; i++){
            result[i] = rand.nextInt((max - min)+1) + min;
        }
        return result;
    }

    /**
     * @param array integers
     * @return String representation of Array
     */
    public static String array2String(int[] array) {
        return Arrays.toString(array);
    }
}
