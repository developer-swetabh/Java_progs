package com.swetabh.javalib;

import java.util.HashSet;

public class FirstDuplicate {

    public static void main(String[] args) {
        int[] arr = {1,2,1,2,3,3};

        int num = getFirstDuplicate(arr);
        System.out.println("The first duplicate number = " + num);

        usingDS(arr);

    }

    private static int usingDS(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();
        for (int i =0;i<arr.length ; i++){
            if(seen.contains(arr[i])){
                return arr[i];
            }else{
                seen.add(arr[i]);
            }
        }
        return -1;
    }

    private static int getFirstDuplicate(int[] arr) {
        int duplicate = -1, prev_dup = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {

            for (int j = arr.length-1; j > i; j--) {

                if (arr[i] == arr[j]) {
                    duplicate = j;
                }
            }

            if (duplicate < prev_dup) {
                prev_dup = duplicate;
            }
        }
        return arr[prev_dup];
    }
}
