package com.swetabh.javalib;

public class ThirdLargestNumber {

    public static void main(String[] args) {
        int[] arr = {1,2};

        int num = getThirdLargestNumebr(arr);
        System.out.println("Third Largest number = " + num);

    }

    private static int getThirdLargestNumebr(int[] arr) {
        Integer max = null;
        Integer second_max = null;
        Integer third_max = null;

        for (Integer num : arr) {

            if (num.equals(max) || num.equals(second_max) || num.equals(third_max)) {
                continue;
            }

            if (max == null || num > max) {
                third_max = second_max;
                second_max = max;
                max = num;
            } else if (second_max == null || num > second_max) {
                third_max = second_max;
                second_max = num;
            } else if (third_max == null || num > third_max) {
                third_max = num;
            }
        }
        if(third_max == null){
            return max;
        }
        return third_max;
    }
}
