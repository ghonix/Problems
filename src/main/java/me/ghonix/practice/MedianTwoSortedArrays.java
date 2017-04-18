package me.ghonix.practice;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 * Created by aghoneim on 4/18/17.
 */
public class MedianTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            nums1 = new int[]{};
        }

        if (nums2 == null) {
            nums2 = new int[]{};
        }

        int size = nums1.length + nums2.length;
        if (size > 0) {
            int firstMedian = 0 , secondMedian = 0;
            int i = 0, j = 0, medianIndex = 0;
            int middle = (size - 1)/ 2;
            while (i < nums1.length || j < nums2.length) {
                int current = 0;
                if (i >= nums1.length) {
                    current = nums2[j];
                    j++;
                } else if (j >= nums2.length) {
                    current = nums1[i];
                    i++;
                } else if (nums1[i] < nums2[j]) {
                    current = nums1[i];
                    i++;
                } else {
                    current = nums2[j];
                    j++;
                }
                if (medianIndex == middle) {
                    firstMedian = current;
                } else if (medianIndex == middle + 1) {
                    secondMedian = current;
                } else if(medianIndex > middle) {
                    break;
                }
                medianIndex++;
            }
            return size % 2 == 0 ? (firstMedian + secondMedian) / 2.0 : firstMedian;
        } else {
            return 0;
        }
    }


    public static void main(String [] args) {
        MedianTwoSortedArrays m = new MedianTwoSortedArrays();
        System.out.println(m.findMedianSortedArrays(new int[]{0, 2, 3, 3}, new int[]{ 4, 5}));
    }

}
