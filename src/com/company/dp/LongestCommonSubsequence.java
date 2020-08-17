package com.company.dp;

public class LongestCommonSubsequence {
    static int mem[][];
    public static void call(int i){
        String a = "abcdgh";
        String b = "abedfhr";
        int maxLength = 0;

        switch (i)
        {
            case 0: maxLength = lcsViaRecursion(a, b, a.length(), b.length());
                break;
            case 1:
                mem = new int[a.length()+1][b.length()+1];
                for(int j = 0; j<=a.length(); j++){
                    for(int k = 0; k<= b.length(); k++){
                        mem[j][k] = -1;
                    }
                }
                maxLength = lcsViaMemoization(a, b, a.length(), b.length());
                break;
            case 2: maxLength = lcsViaTopDown(a, b, a.length(), b.length());
                break;
            default:break;
        }

        System.out.println("Max length of common subsequence is " + maxLength);
    }

    public static int lcsViaRecursion(String a, String b, int l1, int l2){
        // base condition
        if(l1 == 0 || l2 == 0)
        {
            return 0;
        }

        // choice diagram
        if(a.charAt(l1-1) == b.charAt(l2-1)){
            return 1 + lcsViaRecursion(a, b, l1-1, l2-1);
        }
        else{
            return Math.max(lcsViaRecursion(a, b, l1, l2-1), lcsViaRecursion(a, b, l1-1, l2));
        }
    }

    public static int lcsViaMemoization(String a, String b, int l1, int l2){
        // base condition
        if(l1 == 0 || l2 == 0)
        {
            return 0;
        }

        if(mem[l1][l2] != -1)
        {
            return mem[l1][l2];
        }

        // choice diagram
        if(a.charAt(l1-1) == b.charAt(l2-1)){
            return mem[l1][l2] = 1 + lcsViaMemoization(a, b, l1-1, l2-1);
        }
        else{
            return mem[l1][l2] = Math.max(lcsViaMemoization(a, b, l1, l2-1), lcsViaMemoization(a, b, l1-1, l2));
        }
    }

    public static int lcsViaTopDown(String a, String b, int l1, int l2){
        // base condition
        mem = new int[l1+1][l2+1];
        for(int i = 0; i<=l1; i++){
            for(int j = 0; j<= l2; j++){
                if(i == 0 || j == 0)
                {
                    mem[i][j] = 0;
                }
            }
        }

        for(int i = 1; i<=l1; i++){
            for(int j = 1; j<= l2; j++){
                // choice diagram
                if(a.charAt(i-1) == b.charAt(j-1)){
                    mem[i][j] = 1 + mem[i-1][j-1];
                }
                else{
                    mem[i][j]  = Math.max(mem[i][j-1], mem[i-1][j]);
                }
            }
        }

        return mem[l1][l2];
    }
}
