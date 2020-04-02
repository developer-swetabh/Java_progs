package com.swetabh.javalib;

public class MatrixRotation {

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        System.out.println("========== Initial Matrix ========");
        System.out.println();
        printMatrix(arr);


        int result[][] = rotateMatrix(arr);
        System.out.println("========== After rotation ========");
        System.out.println();
        printMatrix(result);

    }

    private static void printMatrix(int[][] result) {
        for (int i = 0; i < 3; i++) {
            System.out.print("[");
            for (int j = 0; j < 3; j++) {

                System.out.print(result[i][j]);
                if (j != 2) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }

    private static int[][] rotateMatrix(int[][] arr) {
        int res[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = arr[2 - j][i];

            }
        }
        return res;
    }

}
