package com.company.dp;

public class SubSetProblems {
    static boolean tab[][];
    static int tab1[][];
    static boolean print = true;
    public static void call(int i){
        int arr[];
        int cap;
        switch (i)
        {
            case 0:
                arr= new int[]{2, 3, 7, 8, 10};
                cap = 11;

                subSetSumProblem(arr, cap, arr.length);
                break;
            case 1:
                arr= new int[]{1, 5, 11, 5};
                equalSumPartitionProblem(arr, arr.length);
                break;
            case 2:
                arr= new int[]{2,3,5,6,8,10};
                cap = 10;
                countOfSubSetSumProblem(arr, cap, arr.length);
                break;
            case 3:
                arr= new int[]{1,6,11,5};
             //   arr= new int[]{1,2,7};
                minimumSubsetDifferenceProblem(arr, arr.length);
                break;
            case 4:
                arr= new int[]{1,1,2,3};
                //   arr= new int[]{1,2,7};
                int diff = 1;
                countOfSubsetWithGivenDifferenceProblem(arr, diff, arr.length);
                break;
            case 5:
                arr= new int[]{1,1,2,3};
                //   arr= new int[]{1,2,7};
                int targetSum = 1;
                targetSumProblem(arr, targetSum, arr.length);
                break;
            default: break;

        }

    }

    public static boolean subSetSumProblem(int arr[], int sum, int size){
        // Initialization
        tab = new boolean[arr.length + 1][sum + 1];
        for(int j = 0; j <=arr.length; j ++)
        {
            for(int k = 0; k <= sum; k++)
            {
//                if(j==0)
//                    tab[j][k] = false;
//                else if(k == 0)
//                    tab[j][k] = true;

                if(k == 0)
                    tab[j][k] = true;
                else if(j==0)
                    tab[j][k] = false;
            }
        }

        // choice diagram
        for(int i = 1; i <= size; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                if(arr[i-1] <= j)
                {
                    tab[i][j] = (tab[i-1][j-arr[i -1]]) || tab[i-1][j];
                }
                else{
                    tab[i][j] = tab[i-1][j];
                }
            }
        }
        if(print) {
            System.out.println("Subset sum exists: " + tab[size][sum]);
        }
        return tab[size][sum];
    }

    public static void equalSumPartitionProblem(int arr[], int size){
        int sum = 0;
        boolean isExists;
        for(int i = 0; i < size; i++)
        {
            sum += arr[i];
        }

        if(sum % 2 != 0)
        {
            isExists = false;
        }
        else{
            print = false;
            isExists = subSetSumProblem(arr, (sum/2), size);
            print = true;
        }


        System.out.println("Equal sum partition exists: " + isExists);
    }

    public static int countOfSubSetSumProblem(int arr[], int sum, int size){
        // Initialization
        tab1 = new int[arr.length + 1][sum + 1];
        for(int j = 0; j <=arr.length; j ++)
        {
            for(int k = 0; k <= sum; k++)
            {
                if(k == 0)
                    tab1[j][k] = 1;
                else if(j==0)
                    tab1[j][k] = 0;
            }
        }

        // choice diagram
        for(int i = 1; i <= size; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                if(arr[i - 1] <= j)
                {
                    tab1[i][j] = tab1[i - 1][j-arr[i - 1]] + tab1[i - 1][j];
                }
                else{
                    tab1[i][j] = tab1[i - 1][j];
                }
            }
        }
        if(print) {
            System.out.println("Subset sum count: " + tab1[size][sum]);
        }
        return tab1[size][sum];
    }

    public static void minimumSubsetDifferenceProblem(int arr[], int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }

        // fill table
        print = false;
        subSetSumProblem(arr, sum, size);
        print = true;

        int[] vector = new int[sum / 2 + 1];
        for (int i = 0; i <= sum / 2; i++) {
            vector[i] = -1;
        }
        int count = 0;
        for (int j = 0; j <= sum / 2; j++) {
            if (tab[size][j]) {
                vector[count++] = j;
            }
        }

        int min = Integer.MAX_VALUE;

        for(int i = 0; i< vector.length; i++)
        {
            if(vector[i] != -1)
            {
                min = Math.min(min, sum - 2*vector[i]);
            }
        }

        System.out.println("Min Subset difference: " + min);
    }

    public static int countOfSubsetWithGivenDifferenceProblem(int arr[], int diff, int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }

        int s1Sum = (sum + diff) / 2;
        // fill table
        print = false;
        int count = countOfSubSetSumProblem(arr, s1Sum, size);
        print = true;

        if(print()) {
            System.out.println("Count Of Subset With Given Difference: " + count);
        }

        return count;
    }

    public static void targetSumProblem(int arr[], int targetSum, int size) {

        int count = countOfSubsetWithGivenDifferenceProblem(arr, targetSum, size);

        System.out.println("Count Of target sum: " + count);
    }

    private static boolean print(){
        StackTraceElement e[] = Thread.currentThread().getStackTrace();
        boolean print = false;
        int counter = 0;
        for (StackTraceElement s : e) {
            if(counter == 3) {   // o for getStackTrace, 1 for print, 2 for parent calling and 3 the parent calling which we want to check.
                print = s.getMethodName().equals("call");
                break;
            }
            counter++;
        }

        return print;
    }
}
