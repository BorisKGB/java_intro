package ru.java.study.lesson1.hw;

public class Task2 {
    /**
     * <p>Дана последовательность целых чисел, оканчивающаяся нулем.
     * Найти сумму положительных чисел, после которых следует отрицательное число.</p>
     * <p>"оканчивающаяся нулем" значит перестаю перебирать элементы как только встретил 0</p>
     * <p>Пример ввода:<br>
     * 1 2 1 2 -1 1 3 1 3 -1 0<br>
     * Логика расчета:<br>
     * 2 -1 переход -> 2 в сумму<br>
     * 3 -1 переход -> 3 в сумму<br>
     * Пример вывода: 5</p>
     */
    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 1, 2, -1, 1, 3, 1, 3, -1, 0};
        System.out.println(sumOfPositiveNumsFollowedByNegativeNums(array));
    }

    /**
     * Count sum of positive numbers that followed by negative numbers. Stop counting when found 0 or end of array
     * @param numbers array of numbers
     * @return sum of suitable elements
     */
    public static int sumOfPositiveNumsFollowedByNegativeNums(int[] numbers) {
        int sum = 0;
        int i = 0;
        while (i<numbers.length-2 && numbers[i+1] != 0) {
            if (numbers[i+1] < 0 && numbers[i] >0) sum += numbers[i];
            i++;
        }
        return sum;
    }
}
