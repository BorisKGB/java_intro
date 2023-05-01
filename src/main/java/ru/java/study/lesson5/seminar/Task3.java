package ru.java.study.lesson5.seminar;

import java.util.Map;

public class Task3 {

    static Map<Character, Integer> romanToArabicMap = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    /**
     * Написать перевод их Римских цифр в арабские:
     */
    public static void main(String[] args) {
        String test1 = "LVIII";
        System.out.println("romanToArabic(LVIII) = " + romanToArabic(test1));
        String test2 = "MCMXCIV";
        System.out.println("romanToArabic(MCMXCIV) = " + romanToArabic(test2));

    }

    private static Integer romanToArabic(String s) {
        int result = 0;
        int i = 0;
        while (i < s.length()){
            int curr = romanToArabicMap.get(s.charAt(i));
            if (i < s.length() - 1){
                int next = romanToArabicMap.get(s.charAt(i+1));
                if (next > curr) {
                    curr = next-curr;
                    i++;
                }
            }
            result += curr;
            i++;
        }
        return result;
    }
}
