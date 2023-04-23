package ru.java.study.lesson1.hw;

public class Task1 {
    /**
     * <a href="https://leetcode.com/problems/reverse-words-in-a-string/">Task on leetcode</a>
     */
    public static void main(String[] args) {
        String[] testCases = {"the sky is blue", "  hello world  ", "a good   example"};
        for (String testCase: testCases) {
            System.out.printf("'%s' -> '%s'\n", testCase, reverseWords(testCase));
        }
    }

    /**
     * Given an input string s, reverse the order of the words.
     * Note that s may contain leading or trailing spaces or multiple spaces between two words.
     * The returned string should only have a single space separating the words. Do not include any extra spaces.
     * @param s Input string that contains words
     * @return String with reverse words order and trimmed of all extra spaces
     */
    private static String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        StringBuilder result = new StringBuilder();
        for (int i = words.length-1;i>=0;i--){
            if (result.length() == 0) {
                result.append(words[i]);
            } else {
                result.append(" ").append(words[i]);
            }
        }
        return result.toString();
    }
}
