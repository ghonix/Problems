package me.ghonix.practice;

import java.util.*;

/**
 * Created by aghoneim on 4/13/17.
 */
public class TwoSum {


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numberSet = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            Integer value = numberSet.get(target - nums[i]);
            if ( value != null) {
                return new int[]{ value, i };
            } else {
                numberSet.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] result = twoSum(new int[] {3, 0, 7, 11, 15}, 15);

        if (result != null) {
            System.out.println(String.format("%d, %d", result[0], result[1]));
        } else {
            System.out.println("Result Not Found!");
        }
    }
}
