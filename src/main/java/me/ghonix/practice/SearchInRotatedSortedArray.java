package me.ghonix.practice;

public class SearchInRotatedSortedArray {
    int search(int[] nums, int target) {
        int rotationIndex = findRotationIndex(nums);
        if (rotationIndex == -1) {
            return binarySearch(nums, target, 0, nums.length -1);
        }
        else if ( rotationIndex > -1 && rotationIndex < nums.length) {
            int result = binarySearch(nums, target, 0, rotationIndex);
            if (result > -1)
                return result;
            result = binarySearch(nums, target, rotationIndex + 1, nums.length - 1);
            return result;
        }
        return -1;
    }

    int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int middle = (end - start)/2 + start;
            if (target < nums[middle]) {
                end = middle - 1;
            } else if (target > nums[middle]) {
                start = middle + 1;
            } else{
                return middle;
            }
        }
        return -1;
    }

    int findRotationIndex(int[] nums) {
        int start = 0;
        int end = nums.length-1;

        while (start <= end) {
            int middle = (end - start)/2 + start;
            int middleValue = nums[middle];
            int startValue = nums[start];
            int endValue = nums[end];
            if (start == middle) {
                break;
            } else if (middleValue < startValue) {
                // the rotation point is to the left
                end = middle;
            } else if (middleValue > endValue) {
                // the rotation point is to the right
                start = middle;
            } else {
                return -1;
            }
        }
        return start;
    }




    public static void main(String [] args) {
        int[] nums = new int[]{1,3,5};
        int target = 6;

        System.out.println(new SearchInRotatedSortedArray().search(nums, target));
    }
}
