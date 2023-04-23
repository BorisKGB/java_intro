package ru.java.study.lesson1.hw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task2Alternative {
    /**
     * <p>Дана последовательность целых чисел, оканчивающаяся нулем.
     * Найти сумму положительных чисел, после которых следует отрицательное число.</p>
     * <p>В данном варианте решения считываю цифры из консоли пока не встречу 0, считаю сразу, не накапливая данные</p>
     * <p>Пример ввода:<br>
     * 1 2 1 2 -1 1 3 1 3 -1 0<br>
     * Логика расчета:<br>
     * 2 -1 переход -> 2 в сумму<br>
     * 3 -1 переход -> 3 в сумму<br>
     * Пример вывода: 5</p>
     */
    public static void main(String[] args) {
        System.out.printf("Sum of positive numbers followed by negative numbers is %d", sumOfPositiveNumsFollowedByNegativeNums());
    }

    /**
     * Read numbers from console, count sum of input positive numbers that followed by negative numbers, stop whet got 0
     * @return sum of suitable numbers
     */
    public static int sumOfPositiveNumsFollowedByNegativeNums() {
        Scanner iScanner = new Scanner(System.in);
        boolean stopFlag = false;
        int lastNum = 0;
        int sum = 0;
        while (!stopFlag){
            int curNum = safeReadInt("Input number > ", iScanner);
            if (curNum < 0 && lastNum > 0) sum += lastNum;
            lastNum = curNum;
            if (curNum == 0) stopFlag = true;
        }
        return sum;
    }

    /**
     * Safe read integer value from iScanner
     * borrowed from <a href="https://stackoverflow.com/questions/25277286/exception-handling-with-scanner-nextint-vs-scanner-nextline">stackoverflow</a>
     * @param greetingText greeting for user to input
     * @param iScanner scanner to read from
     * @return integer
     */
    public static int safeReadInt(String greetingText, Scanner iScanner) {
        // borrowed from https://stackoverflow.com/questions/25277286/exception-handling-with-scanner-nextint-vs-scanner-nextline
        boolean continueInput = true;
        int result = 0;

        do {
            try{
                System.out.print(greetingText);
                result = iScanner.nextInt();
                continueInput = false;
            }
            catch (InputMismatchException ex) {
                System.out.println("Try again. (Incorrect input: an integer is required)");
                iScanner.nextLine();
            }
        }
        while (continueInput);
        return result;
    }
}
