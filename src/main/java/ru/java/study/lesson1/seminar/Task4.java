package ru.java.study.lesson1.seminar;

import java.math.BigDecimal;

public class Task4 {
    /**
     * <a href="https://leetcode.com/problems/add-binary/">Add Binary</a>
     */
    public static void main(String[] args) {
        System.out.printf("%s + %s = %s\n", "11", "1", addBinary("11", "1"));
        System.out.printf("%s + %s = %s\n", "1010", "1011", addBinary("1010", "1011"));
    }

    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int maxLen = Math.max(a.length(), b.length());
        boolean additionOverflow = false;
        for (int i = 0; i < maxLen; i++) {
            char aVal = (i < a.length()) ? a.charAt(a.length()-1-i) : '0';
            char bVal = (i < b.length()) ? b.charAt(b.length()-1-i) : '0';
            int overflow = (additionOverflow) ? 1:0;
            int left = (aVal == '1') ? 1:0;
            int right = (bVal == '1') ? 1:0;
            int additionResult = add(overflow, left, right);
            switch (overflow+left+right){
                case 0:
                    additionOverflow = false;
                    result.append('0');
                    break;
                case 1:
                    additionOverflow = false;
                    result.append('1');
                    break;
                case 2:
                    additionOverflow = true;
                    result.append('0');
                    break;
                case 3:
                    additionOverflow = true;
                    result.append('1');
                    break;
            }
        }
        if (additionOverflow) result.append('1');
        return result.reverse().toString();
    }

    private static int add(int overflowBuffer, int left, int right) {
        return add(add(left, right), overflowBuffer);
    }

    private static int add(int left, int right) {
        return left+right;
    }
}
