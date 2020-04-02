package com.swetabh.javalib;

import java.util.Arrays;

public class FirstNonRepeated {

    public static void  main(String[] args){
        System.out.println("Hello World");
        String str = "aaabcccdeeef";
        checkNonRepeated(str);
        int[] arr = {-6,-3,-2,1,4,5};
        int [] result = sortedSquareArray(arr);
        System.out.println("Sorted Square Array = " + Arrays.toString(result));

    }

    private static void checkNonRepeated(String str) {
        for(int i =0 ;i<str.length();i++){
            boolean duplicate = false;
            for(int j=0 ;j<str.length();j++){
                if((str.charAt(i)==str.charAt(j)) && (i!=j)){
                    duplicate = true;
                    break;
                }
            }
            if(!duplicate){
                System.out.println("First non repeated character : " + str.charAt(i));
                break;
            }
        }
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
