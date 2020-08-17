package com.company.dp;

public class ZeroOneKnapsack {
    static int mem[][];
    static int tab[][];
    public static void call(int i){
        int profit = 0;
        int wt[]= {1,3,4,5};
        int val[]= {1,4,5,7};
        int cap = 7;
        switch (i)
        {
            case 0: profit = maxProfitViaRecursion(wt, val, cap, wt.length);
                break;
            case 1:
                mem = new int[wt.length + 1][cap + 1];
                for(int j = 0; j <=wt.length; j ++)
                {
                    for(int k = 0; k <= cap; k++)
                    {
                        mem[j][k] = -1;
                    }
                }
                profit = maxProfitViaMemoization(wt, val, cap, wt.length);
                break;
            case 2:
                tab = new int[wt.length + 1][cap + 1];
                for(int j = 0; j <=wt.length; j ++)
                {
                    for(int k = 0; k <= cap; k++)
                    {
                        if(j ==0 || k ==0)
                        tab[j][k] = 0;
                    }
                }
                profit = maxProfitViaTopDown(wt, val, cap, wt.length);
                break;
            default:break;
        }

        System.out.println("Max profit is " + profit);
    }

    public static int maxProfitViaRecursion(int wt[], int val[], int cap, int size){
        // base condition
        if(size == 0 || cap == 0)
        {
            return 0;
        }

        // choice diagram
        if(wt[size-1] <= cap)
        {
            return Math.max((val[size-1]+ maxProfitViaRecursion(wt, val, cap - wt[size-1], size -1)), maxProfitViaRecursion(wt, val, cap, size -1));
        }

        return maxProfitViaRecursion(wt, val, cap, size -1);
    }


    public static int maxProfitViaMemoization(int wt[], int val[], int cap, int size){
        // base condition
        if(mem[size][cap] != -1)
        {
            return mem[size][cap];
        }

        // choice diagram
        if(wt[size-1] <= cap)
        {
            return mem[size][cap] = Math.max((val[size-1]+ maxProfitViaRecursion(wt, val, cap - wt[size-1], size -1)), maxProfitViaRecursion(wt, val, cap, size -1));
        }

        return mem[size][cap] = maxProfitViaRecursion(wt, val, cap, size -1);
    }

    public static int maxProfitViaTopDown(int wt[], int val[], int cap, int size){
        // choice diagram
        for(int i = 1; i <= size; i++)
        {
            for (int j = 1; j <= cap; j++)
            {
                if(wt[i-1] <= j)
                {
                    tab[i][j] = Math.max((val[i-1]+ tab[i-1][j-wt[i -1]]), tab[i-1][j]);
                }
                else{
                    tab[i][j] = tab[i-1][j];
                }
            }
        }

        return tab[size][cap];
    }
}
