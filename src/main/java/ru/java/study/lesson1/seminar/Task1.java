package ru.java.study.lesson1.seminar;

public class Task1 {
    /**
     * <a href="https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/description/">
     *     Subtract the Product and Sum of Digits of an Integer</a>
     */
    public static void main(String[] args) {
        System.out.printf("%d -> %d\n", 234, subtractProductAndSum(234));
        System.out.printf("%d -> %d\n", 4421, subtractProductAndSum(4421));
    }

    /**
     * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
     * @param n Number
     * @return difference between the product of its digits and the sum of its digits
     */
    public static int subtractProductAndSum(int n) {
        int[] digits = int2digits(n);
        return mulDigits(digits) - sumDigits(digits);
    }

    /**
     * Split number into digits
     * @param n Number
     * @return array of digits
     */
    private static int[] int2digits(int n) {
        int numberDigits = 1;
        while (n / (Math.pow(10,numberDigits)) >= 1) numberDigits++;
        int[] result = new int[numberDigits];
        while (n > 0) {
            result[numberDigits-1] = n % 10;
            n = n / 10;
            numberDigits--;
        }
        return result;
    }

    /**
     * Multiply digits
     * @param digits array of numbers
     * @return multiplication result of all digits
     */
    public static int mulDigits(int[] digits) {
        int result = digits[0];
        for (int i = 1; i < digits.length; i++) {
            result *= digits[i];
        }
        return result;
    }

    /**
     * Sum numbers
     * @param digits array of numbers
     * @return sum result of all digits
     */
    public static int sumDigits(int[] digits) {
        int result = digits[0];
        for (int i = 1; i < digits.length; i++) {
            result += digits[i];
        }
        return result;
    }
}
