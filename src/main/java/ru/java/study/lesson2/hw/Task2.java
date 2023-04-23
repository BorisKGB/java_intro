package ru.java.study.lesson2.hw;

public class Task2 {
    /**
     * Дана последовательность из N целых чисел. Верно ли, что последовательность является возрастающей.
     */
    public static void main(String[] args) {
        int[] array = new int[] {1, 3, 4, 5, 8};
        int[] generatedArray = Lib.generateNumbersArray(5, 0, 10);
        System.out.printf("%s -> %b\n", Lib.array2String(array), isIncreasing(array));
        System.out.printf("%s -> %b\n", Lib.array2String(generatedArray), isIncreasing(generatedArray));
    }

    /**
     * @param array numbers array
     * @return boolean true if numbers sequence is increasing
     */
    private static boolean isIncreasing(int[] array) {
        int i = 1;
        boolean increasing = true;
        while (i < array.length && increasing){
            if (array[i] < array[i-1]) increasing = false;
            i++;
        }
        return increasing;
    }
}
