package ru.java.study.lesson4.hw;

import java.util.Stack;

public class Task2 {
    /**
     * <a href="https://leetcode.com/problems/valid-parentheses/">Valid Parentheses</a>
     */
    public static void main(String[] args) {
        String sample1 = "()";
        System.out.printf("isValid('%s') = %b\n", sample1, isValidTry2(sample1));
        String sample2 = "()[]{}";
        System.out.printf("isValid('%s') = %b\n", sample2, isValidTry2(sample2));
        String sample3 = "(]";
        System.out.printf("isValid('%s') = %b\n", sample3, isValidTry2(sample3));
        String sample4 = "([)]";
        System.out.printf("isValid('%s') = %b\n", sample4, isValidTry2(sample4));
        String sample5 = "{[]}";
        System.out.printf("isValid('%s') = %b\n", sample5, isValidTry2(sample5));
        String sample6 = "(])";
        System.out.printf("isValid('%s') = %b\n", sample6, isValidTry2(sample6));
    }

    /**
     * My original minimal working solution, not happy with results and complexity
     * @param s string
     * @return brackets validity
     */
    public static boolean isValid(String s) {
        Stack<Character> brackets = new Stack<>();
        int idx = 0;
        while (idx < s.length()){
            Character current = s.charAt(idx);
            if (current.equals('(') || current.equals('{') || current.equals('[')) brackets.add(current);
            else if (current.equals(')') || current.equals('}') || current.equals(']')) {
                if (brackets.isEmpty()) return false;
                if (current.equals(')') && brackets.peek().equals('(')) brackets.pop();
                else if (current.equals('}') && brackets.peek().equals('{')) brackets.pop();
                else if (current.equals(']') && brackets.peek().equals('[')) brackets.pop();
                else return false;
            }
            idx++;
        }
        return brackets.empty();
    }

    /**
     * Adapted solution from leetcode examples
     */
    public static boolean isValidTry2(String s) {
        Stack<Character> brackets = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') brackets.push(')');
            else if (c == '{') brackets.push('}');
            else if (c == '[') brackets.push(']');
            else if (brackets.isEmpty() || brackets.pop() != c) return false;
        }
        return brackets.empty();
    }
}
