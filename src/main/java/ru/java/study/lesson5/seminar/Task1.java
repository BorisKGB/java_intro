package ru.java.study.lesson5.seminar;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    /**
     * Посчитать количество вхождений каждого символа в текст.
     */
    public static void main(String[] args) {
        printMap(countSymbols("abbacd"));
    }

    public static Map<Character, Integer> countSymbols(String s) {
        Map<Character, Integer> symbolsDictionary = new HashMap<>();
        for (char ch : s.toCharArray()) {
            Integer currCharCount = symbolsDictionary.getOrDefault(ch, 0);
            symbolsDictionary.put(ch, currCharCount+1);
        }
        return symbolsDictionary;
    }
    public static void printMap(Map<Character, Integer> map) {
//        for (Map.Entry<Character, Integer> item : map.entrySet()) { // <- more optimized way
        for (var item : map.entrySet()) {
            System.out.printf("[%s -> %d]\n", item.getKey(), item.getValue());
        }
    }
}
