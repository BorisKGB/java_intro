package ru.java.study.lesson2.hw;

public class Task1 {
    /**
     * Дана последовательность N целых чисел. Найти сумму простых чисел.
     */
    public static void main(String[] args) {
        int[] numbers = Lib.generateNumbersArray(5, 0, 10);
        System.out.printf("%s -> %d\n", Lib.array2String(numbers), sumOfPrimeNumbers(numbers));
    }

    /**
     * @param numbers array of numbers
     * @return sum of prime numbers
     */
    private static int sumOfPrimeNumbers(int[] numbers) {
        int result = 0;
        for (int number : numbers) {
            if (isPrimeNumber(number)) result += number;
        }
        return result;
    }

    /**
     * @param number integer
     * @return boolean, true if number is prime
     */
    public static boolean isPrimeNumber(int number) {
        for (int i = 2; i<=Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

}
