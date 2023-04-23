package ru.java.study.lesson1.seminar;

import java.util.Arrays;

public class Task6 {
    /**
     * <a href="https://leetcode.com/problems/running-sum-of-1d-array/description/">Running Sum of 1d Array</a>
     */
    public static void main(String[] args) {
        int[] sample1 = {1,2,3,4};
        int[] sample2 = {1,1,1,1,1};
        int[] sample3 = {3,1,2,10,1};
        System.out.printf("%s -> %s\n", Arrays.toString(sample1), Arrays.toString(runningSum(sample1)));
        System.out.printf("%s -> %s\n", Arrays.toString(sample2), Arrays.toString(runningSum(sample2)));
        System.out.printf("%s -> %s\n", Arrays.toString(sample3), Arrays.toString(runningSum(sample3)));
    }

    /**
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     * @param nums numbers
     * @return running sum of nums
     */
    public static int[] runningSum(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i=1; i< nums.length; i++){
            res[i] = res[i-1] + nums[i];
        }
        return res;
    }
}
