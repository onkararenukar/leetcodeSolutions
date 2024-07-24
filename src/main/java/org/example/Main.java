package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//      -------------------------------------------------------------
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}; // Input array
//        int[] expectedNums = {0, 1, 2, 3, 4}; // The expected answer with correct length
//
//        int k = removeDuplicates(nums); // Calls your implementation
//
//        assert k == expectedNums.length;
//        for (int i = 0; i < k; i++) {
//            assert nums[i] == expectedNums[i];
//        }

//        ----------------------------------------------------------

        intersection();
    }

    public static int removeDuplicates(int[] nums) {
        //METHOD-1
        int[] numsArr = Arrays.stream(nums).distinct().sorted().toArray();
        System.arraycopy(numsArr, 0, nums, 0, numsArr.length);
        Arrays.stream(nums).forEach(System.out::print);
        return numsArr.length;

        //METHOD-2 Optimized using two pointer technique
//        if (nums.length == 0) return 0;
//        // First, sort the array
//        Arrays.sort(nums);
//        // Use a two-pointer technique to remove duplicates in place
//        int uniqueIndex = 0; // Index of the last unique element
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] != nums[uniqueIndex]) {
//                uniqueIndex++;
//                nums[uniqueIndex] = nums[i];
//            }
//        }
//        // Return the length of the unique elements
//        return uniqueIndex + 1;
    }

    public static int[] intersection() {
        int[] iArr = {1,2,2,3,2,1,1};
        int[] iBrr = {1,2};
        List<Integer> intersection=new ArrayList<>();

        int[] iArrMod = Arrays.stream(iArr).distinct().toArray();
        int[] iBrrMod = Arrays.stream(iBrr).distinct().toArray();
        int[] inter = new int[Math.max(iArrMod.length, iBrrMod.length)];
int k=0;
        for(int i=0;i<iArrMod.length;i++){
            for(int j=0;j<iBrrMod.length;j++){
                if(iArrMod[i]==iBrrMod[j] && !intersection.contains(iArrMod[i])){
                    intersection.add(iArrMod[i]);
                }
            }
        }

        intersection.forEach(System.out::println);
        return intersection.toArray();
    }
}
