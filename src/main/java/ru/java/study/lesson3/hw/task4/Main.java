package ru.java.study.lesson3.hw.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    /**
     *  4. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее из этого списка.
     */
    public static void main(String[] args) {
        List<Integer> numbers = generateList(10, -10, 10);
        System.out.println(numbers);
        findAndPrintMinMaxMedian(numbers);
    }

    /**
     * Calculate and print minimum, average and maximum values of a List<br>
     * Also for test purposes use two different ways to find minimum and maximum values
     * Also search for number closest to average in List
     * @param numbers list of integers
     */
    private static void findAndPrintMinMaxMedian(List<Integer> numbers) {
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        double sum = 0d;
        for (Integer number: numbers) {
            if (number > max) max = number;
            if (number < min) min = number;
            sum += number;
        }
        double average = sum/numbers.size();
        System.out.printf("Min value = %d\n", min);
        System.out.printf("Average value = %.0f / %d =  %.2f\n", sum, numbers.size(), average);
        System.out.printf("Max value = %d\n", max);
        System.out.println("---alternative way to get min and max values---");
        System.out.printf("Min value = %d\n", Collections.min(numbers));
        System.out.printf("Max value = %d\n", Collections.max(numbers));
        int closestToAverageIdx = numbers.indexOf((int) average);
        if (closestToAverageIdx == -1) {
            closestToAverageIdx = 0;
            for (int i = 1; i < numbers.size(); i++) {
                if (Math.abs(average - numbers.get(i)) < Math.abs(average - numbers.get(closestToAverageIdx)))
                    closestToAverageIdx = i;
            }
        }
        System.out.printf("number in List closest to Average value is '%d' and on index '%d'", numbers.get(closestToAverageIdx), closestToAverageIdx);
    }

    /**
     * @param size number of elements
     * @param min minimum value to generate
     * @param max maximum value to generate
     * @return generated List of integers in range [min, max]
     */
    public static List<Integer> generateList(int size, int min, int max) {
        List<Integer> result = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++){
            result.add(rand.nextInt((max - min)+1) + min);
        }
        return result;
    }

}
