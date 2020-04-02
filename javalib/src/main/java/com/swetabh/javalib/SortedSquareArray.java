package com.swetabh.javalib;

import java.util.Arrays;

public class SortedSquareArray {

    public static void main(String[] args){
        int[] arr = {-6,-3,-2,1,4,5};
        int [] result = sortedSquareArray(arr);
        System.out.println("Sorted Square Array = " + Arrays.toString(result));
    }

    private static int[] sortedSquareArray(int[] arr) {
        int[] result = new int[arr.length];
        int left=0,right=arr.length-1;
        for(int i = arr.length-1 ;i>=0;i--){
            if(Math.abs(arr[left]) > arr[right]){
                result[i] = arr[left]*arr[left];
                left++;
            } else {
                result[i] = arr[right]*arr[right];
                right--;
            }
        }
        return result;
    }
}
