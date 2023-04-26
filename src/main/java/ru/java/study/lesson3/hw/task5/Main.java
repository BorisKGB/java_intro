package ru.java.study.lesson3.hw.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.java.study.lesson3.hw.task4.Main.generateList;

public class Main {
    /**
     * 5*) при входном массиве, вернуть массив массивов со всеми перестановками его элементов.<br>
     * Пример входных данных:<br>
     * [1,2,3]<br>
     * Пример выходных данных:<br>
     * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public static void main(String[] args) {
        List<Integer> inputArray = setArray();
        List<Integer> generatedArray = generateList(3, 0, 10);
        List<List<Integer>> shuffles = possibleShuffles(inputArray);
        List<List<Integer>> shufflesGenerated = possibleShuffles(generatedArray);
        System.out.printf("%s -> %s\n", inputArray, shuffles);
        System.out.printf("%s -> %s\n", generatedArray, shufflesGenerated);
    }

    /**
     * @param numbers list of numbers to shuffle
     * @return possible variants of numbers shuffle
     */
    private static List<List<Integer>> possibleShuffles(List<Integer> numbers) {
        List<List<Integer>> result = new ArrayList<>();
        if (numbers.size() == 1) {
            result.add(numbers);
        } else {
            for (Integer number : numbers) {
                // todo Not sure how to do List copy correctly, other variants was "new ArrayList<Integer>(numbers);" and "new ArrayList<>(Collections.nCopies(numbers.size(), 0));"
                List<Integer> forShuffle = new ArrayList<>(Arrays.asList(new Integer[numbers.size()]));
                Collections.copy(forShuffle, numbers);
                forShuffle.remove(number);
                for (List<Integer> shuffle : possibleShuffles(forShuffle)) {
                    List<Integer> shuffleVariant = new ArrayList<>();
                    shuffleVariant.add(number);
                    shuffleVariant.addAll(shuffle);
                    result.add(shuffleVariant);
                }
            }
        }
        return result;
    }

    /**
     * @return List of numbers
     */
    private static List<Integer> setArray() {
        List<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        return array;
    }
}
