package com.company.dp;

public class LongestCommonSubsequence {
    static int mem[][];
    static String memS[][];
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
            case 3: maxLength = lcSubStringViaTopDown(a, b, a.length(), b.length());
                System.out.println("Max length of common sub string is " + maxLength);
                return;
            case 4:
                String maxStr = printLsViaTopDown(a, b, a.length(), b.length());
                String maxStr1 = printLsViaTopDown1(a, b, a.length(), b.length());
                System.out.println("common sub sequence 1 is " + maxStr);
                System.out.println("common sub sequence 2 is " + maxStr);
                return;
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

    public static int lcSubStringViaTopDown(String a, String b, int l1, int l2){
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
                    mem[i][j]  = 0;
                }
            }
        }

        int max = 0;
        for(int i = 1; i<=l1; i++){
            for(int j = 1; j<= l2; j++){
                    max = Math.max(max, mem[i][j]);
            }
        }

        return max;
    }

    public static String printLsViaTopDown(String a, String b, int l1, int l2){
        // base condition
        memS = new String[l1+1][l2+1];
        for(int i = 0; i<=l1; i++){
            for(int j = 0; j<= l2; j++){
                if(i == 0 || j == 0)
                {
                    memS[i][j] = "";
                }
            }
        }

        for(int i = 1; i<=l1; i++){
            for(int j = 1; j<= l2; j++){
                // choice diagram
                if(a.charAt(i-1) == b.charAt(j-1)){
                    memS[i][j] = memS[i-1][j-1] + a.charAt(i-1);
                }
                else{
                    memS[i][j]  = memS[i][j-1].length() > memS[i-1][j].length() ? memS[i][j-1] : memS[i-1][j];
                }
            }
        }

        return memS[l1][l2];
    }

    public static String printLsViaTopDown1(String a, String b, int l1, int l2){
        lcsViaTopDown(a, b, l1, l2);

        int i = l1;
        int j = l2;
        String result = "";
        while(i > 0 &&  j >0){
            if(a.charAt(i-1) == b.charAt(j-1)){
                i--;
                j--;
            }
            else{
                if(mem[i -1][j] > mem[i][j -1]){
                    i--;
                }
                else{
                    j--;
                }
            }
        }
        int len = result.length();
        if(len > 0){
            result = new StringBuilder(result).reverse().toString();
        }
        return result;
    }
}
