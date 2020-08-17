package com.company.dp;

public class UnboundedKnapsack {
    static int tab[][];
    public static void call(int i){
        int profit = 0;
        int sum = 0;
        int val[];
        switch (i)
        {
            case 0:
                val= new int[]{1,5,8,9,10,17,17,20};
                int cap = val.length;   // length of rod, can be different from val arr length in that case use length of rod here.

                int length[] = new int[cap];  // create if length array not given
                for (int j = 0; j< cap; j++)
                {
                    length[j] = j+1;
                }
                int[] wt = length;

                tab = new int[wt.length + 1][cap + 1];
                for(int j = 0; j <=wt.length; j++)
                {
                    for(int k = 0; k <= cap; k++)
                    {
                        if(j ==0 || k ==0)
                            tab[j][k] = 0;
                    }
                }
                profit = rodCuttingProblem(val, wt, wt.length);
                System.out.println("Max profit is " + profit);
                break;
            case 1:
                val= new int[]{1,2,3,5};
                sum = 8;

                tab = new int[val.length + 1][sum + 1];
                for(int j = 0; j <=val.length; j++)
                {
                    for(int k = 0; k <= sum; k++)
                    {
                        if(j ==0 )
                            tab[j][k] = 0;
                        else if(k ==0)
                            tab[j][k] = 1;
                    }
                }
                profit = coinChangeMaxNumberOfWaysProblem(val, sum);
                System.out.println("Coin change number of ways is " + profit);
                break;
            case 2:
                val= new int[]{3,5,2};
                sum = 5;

                tab = new int[val.length + 1][sum + 1];
                for(int j = 0; j <=val.length; j++)
                {
                    for(int k = 0; k <= sum; k++)
                    {
                        if(k ==0)
                            tab[j][k] = 0;
                        else if(j ==0 )
                            tab[j][k] = Integer.MAX_VALUE - 1;
                    }
                }

                for(int j = 1; j <= sum; j++)
                {
                    if(j% val[0] == 0)
                    {
                        tab[1][j] = j / val[0];
                    }
                    else{
                        tab[1][j] = Integer.MAX_VALUE - 1;
                    }
                }
                profit = coinChangeMinNumberOfCoinsProblem(val, sum);
                System.out.println("Coin change min number of coin is " + profit);
                break;
            default:break;
        }
    }

    public static int rodCuttingProblem(int val[],int[] wt, int cap){
        // choice diagram

        for(int i = 1; i <= cap; i++)
        {
            for (int j = 1; j <= cap; j++)
            {
                if(wt[i-1] <= j)
                {
                    tab[i][j] = Math.max((val[i-1]+ tab[i][j-wt[i -1]]), tab[i-1][j]);
                }
                else{
                    tab[i][j] = tab[i-1][j];
                }
            }
        }

        return tab[cap][cap];
    }

    public static int coinChangeMaxNumberOfWaysProblem(int[] wt, int cap){
        for(int i = 1; i <= wt.length; i++)
        {
            for (int j = 1; j <= cap; j++)
            {
                if(wt[i-1] <= j)
                {
                    tab[i][j] = (tab[i][j-wt[i -1]]) + tab[i-1][j];
                }
                else{
                    tab[i][j] = tab[i-1][j];
                }
            }
        }

        return tab[wt.length][cap];
    }

    public static int coinChangeMinNumberOfCoinsProblem(int[] wt, int cap){
        for(int i = 1; i <= wt.length; i++)
        {
            for (int j = 2; j <= cap; j++)
            {
                if(wt[i-1] <= j)
                {
                    tab[i][j] = Math.min((1 + tab[i][j-wt[i -1]]), tab[i-1][j]);
                }
                else{
                    tab[i][j] = tab[i-1][j];
                }
            }
        }

        return tab[wt.length][cap];
    }
}
