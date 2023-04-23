package ru.java.study.lesson2.hw;

public class Task3 {
    /**
     * Дан массив целых чисел. Заменить отрицательные элементы на сумму индексов двузначных элементов массива.
     */
    public static void main(String[] args) {
        int[] array = new int[] {1, 3, -5, 12, 18, -10, 5, 8};
        int[] generatedArray = Lib.generateNumbersArray(10, -100, 100);
        System.out.printf("%s -> %s\n", Lib.array2String(array), Lib.array2String(replaceNegativeToIndexSum(array)));
        System.out.printf("%s -> %s\n", Lib.array2String(generatedArray), Lib.array2String(replaceNegativeToIndexSum(generatedArray)));
    }

    /**
     * @param array numbers
     * @return new array with negative numbers replaced to sum of indexes of 2-digit numbers
     */
    private static int[] replaceNegativeToIndexSum(int[] array) {
        int[] result = new int[array.length];
        int sumNegIndexes = 0;
        int firstNegIndex = -1;
        int lastNegIndex = -1;
        for (int i = 0; i < array.length; i++) {
            int elem = array[i];
            if (elem < 0){
                if (firstNegIndex < 0) firstNegIndex = i;
                lastNegIndex = i;
            }
            if ((9 < elem && elem <= 99) || (-99 <= elem && elem < -9) ) sumNegIndexes += i;
            result[i] = elem;
        }
        for (int i = firstNegIndex; i <= lastNegIndex; i++) {
            if (result[i] < 0) result[i] = sumNegIndexes;
        }
        return result;
    }
}
