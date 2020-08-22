package com.company.dp;

public class MatrixChainMultiplication {
    static int[][] t = {};
    public static void call(int i){
        int arr[] = {40, 20, 30, 10, 30 };
        int minCost = 0;
        String text;

        switch (i) {
            case 0:
               minCost = matrixChainMultiplicationMinCostViaRecursion(arr, 1, arr.length - 1);
                System.out.println("Matrix chain multiplication rec- min cost is " + minCost);
                break;
            case 1:
                t = new int[arr.length][arr.length];
                for (int j = 0; j < arr.length; j++){
                    for (int k = 0; k < arr.length; k++){
                        t[j][k] = -1;
                    }
                }
                minCost = matrixChainMultiplicationMinCostViaBottomUp(arr, 1, arr.length - 1);
                System.out.println("Matrix chain multiplication memoization - min cost is " + minCost);
                break;
            case 2:
                text = "nitik";
                minCost = palindromePartitioningRecursive(text, 0, arr.length - 1);
                System.out.println("palindrome partition rec- min cost is " + minCost);
                break;

            case 3:
                text = "nitik";
                t = new int[text.length()][text.length()];
                for (int j = 0; j < arr.length; j++){
                    for (int k = 0; k < arr.length; k++){
                        t[j][k] = -1;
                    }
                }
                minCost = palindromePartitioningBottomUp(text, 0, arr.length - 1);
                System.out.println("palindrome partition rec- min cost is " + minCost);
                break;
        }
    }

    private static int matrixChainMultiplicationMinCostViaRecursion(int[] arr, int i, int j){
        if(i >= j){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
           int tmp = matrixChainMultiplicationMinCostViaRecursion(arr, i, k) + matrixChainMultiplicationMinCostViaRecursion(arr, k+1, j) + arr[i-1]* arr[k] * arr[j];

           min = Math.min(min, tmp);
        }

        return min;
    }

    private static int matrixChainMultiplicationMinCostViaBottomUp(int[] arr, int i, int j){
        if(i >= j){
            return 0;
        }

        if(t[i][j] != -1){
            return t[i][j];
        }

        int min = Integer.MAX_VALUE;

        for(int k = i; k < j; k++){
            int tmp = matrixChainMultiplicationMinCostViaBottomUp(arr, i, k) + matrixChainMultiplicationMinCostViaBottomUp(arr, k+1, j) + arr[i-1]* arr[k] * arr[j];

            min = Math.min(min, tmp);
        }

        return t[i][j] = min;
    }

    private static int palindromePartitioningRecursive(String text, int i, int j){
        if(i >= j){
            return 0;
        }

        if(isPalindrome(text, i, j)){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int tmp = palindromePartitioningRecursive(text, i, k) + palindromePartitioningRecursive(text, k+1, j) + 1;

            min = Math.min(min, tmp);
        }

        return min;
    }

    private static int palindromePartitioningBottomUp(String text, int i, int j){
        if(i >= j){
            return 0;
        }

        if(isPalindrome(text, i, j)){
            return 0;
        }

        if(t[i][j] != -1){
            return t[i][j];
        }

        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            if(t[i][k] != -1){
                left = t[i][k];
            }
            else{
                left = palindromePartitioningRecursive(text, i, k);
            }

            if(t[k+1][j] != -1){
                right = t[k+1][j];
            }
            else{
                right = palindromePartitioningRecursive(text, k+1, j);
            }
            int tmp = left + right + 1;

            min = Math.min(min, tmp);
        }

        return t[i][j] = min;
    }

    private static boolean isPalindrome(String text, int i, int j){
        if(i == j){
            return true;
        }

        if(i > j){
            return true;
        }

        while (i < j){
            if(text.charAt(i++) != text.charAt(j--)){
                return false;
            }
        }

        return true;
    }
}
