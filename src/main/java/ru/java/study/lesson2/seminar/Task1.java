package ru.java.study.lesson2.seminar;

public class Task1 {
    /**
     * <a href="https://leetcode.com/problems/powx-n/">Pow(x, n)</a>
     */
    public static void main(String[] args) {
        System.out.println(myPow2(2, -2147483648));
    }

    /**
     * Trying to count pow with some optimizations
     * when `n<-1074` return 0d cause result number will be too small to count
     * @param x number
     * @param n power
     * @return pow result
     */
    public static double myPow(double x, int n) {
        if (n == 0) return 1d;
        if (n < -1074) return 0d;
        if (Math.abs(x) == 1) return (n%2 == 0)? x:-x;
        double result = 1d;
        while (n != 0) {
            if (n < 0) {
                result /= x;
                n++;
            } else {
                result *= x;
                n--;
            }
        }
        return result;
    }
    public static double myPow2(double x, int n) {
        if(n < 0){
            n = -n;
            x = 1 / x;
        }
        double pow = 1;
        while(n != 0){
            if((n & 1) != 0){
                pow *= x;
            }
            x *= x;
            n >>>= 1;
        }
        return pow;
    }
}
