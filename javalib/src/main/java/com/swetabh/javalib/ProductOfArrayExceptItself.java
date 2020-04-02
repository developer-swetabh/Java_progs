package com.swetabh.javalib;

import java.util.Arrays;

public class ProductOfArrayExceptItself {

    public static void main(String[] args) {
        System.out.println("Hello World");
        int[] arr = {1, 2, 3, 4};
        int[] prdtArray = productExceptSelf(arr);
        System.out.println("Product Arrays = " + Arrays.toString(prdtArray));
        System.out.println("============= Using DS =============");
        int[] dsArr = OptimisedFun(arr);
        System.out.println("Product Arrays = " + Arrays.toString(dsArr));

    }

    private static int[] OptimisedFun(int[] arr) {
        int[] left_prod = new int[arr.length];
        int[] right_prod = new int[arr.length];
        int[] output = new int[arr.length];
        left_prod[0] =1;
        right_prod[arr.length-1]=1;
        for (int i = 1; i < arr.length; i++) {
            left_prod[i] = left_prod[i-1] * arr[i-1];
        }

        for (int i = arr.length-2; i >=0; i--) {
            right_prod[i] = right_prod[i+1] * arr[i+1];
        }

        for (int i = 0; i<arr.length; i++) {
            output[i] = left_prod[i] * right_prod[i];
        }

        return output;
    }

    private static int[] productExceptSelf(int[] arr) {
        int[] productArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int product = 1;
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                product = product * arr[j];
            }
            productArr[i] = product;
        }
        return productArr;
    }

}
