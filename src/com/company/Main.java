package com.company;

import com.company.dp.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        for (int i =0; i<=2; i++)
        {
            ZeroOneKnapsack.call(i);
        }
        for (int i =0; i<=5; i++)
        {
            SubSetProblems.call(i);
        }

        for (int i =0; i<=2; i++)
        {
            UnboundedKnapsack.call(i);
        }

        for (int i =0; i<=12; i++)
        {
            LongestCommonSubsequence.call(i);
        }

        for (int i =0; i<=4; i++)
        {
            MatrixChainMultiplication.call(i);
        }
    }
}
