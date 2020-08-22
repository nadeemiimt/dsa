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
                System.out.println("Max length of common subsequence is " + maxLength);
                break;
            case 1:
                mem = new int[a.length()+1][b.length()+1];
                for(int j = 0; j<=a.length(); j++){
                    for(int k = 0; k<= b.length(); k++){
                        mem[j][k] = -1;
                    }
                }
                maxLength = lcsViaMemoization(a, b, a.length(), b.length());
                System.out.println("Max length of common subsequence is " + maxLength);
                break;
            case 2: maxLength = lcsViaTopDown(a, b, a.length(), b.length());
                System.out.println("Max length of common subsequence is " + maxLength);
                break;
            case 3: maxLength = lcSubStringViaTopDown(a, b, a.length(), b.length());
                System.out.println("Max length of common sub string is " + maxLength);
                return;
            case 4:
                String maxStr = printLsViaTopDown(a, b, a.length(), b.length());
                String maxStr1 = printLsViaTopDown1(a, b, a.length(), b.length());
                System.out.println("common sub sequence 1 is " + maxStr);
                System.out.println("common sub sequence 2 is " + maxStr1);
                return;
            case 5:
                a = "AGGTAB";
                b = "GXTXAYB";    // "AGGXTXAYB"
                maxLength = lengthOfShortestCommonSuperSequenceViaTopDown(a, b, a.length(), b.length());
                System.out.println("common super sequence length is " + maxLength);
                return;
            case 6:
                a = "heap";
                b = "pea";    // "AGGXTXAYB"
                maxStr = minNumberOfInsertionAndDeletionRequiredToConvertOneToOtherStringViaTopDown(a, b, a.length(), b.length());
                System.out.println(maxStr);
                return;
            case 7:
                a = "agbcba";// "abcba"
                maxLength = longestPalindromicSubsequenceViaTopDown(a, a.length());
                System.out.println("Longest palindromic subsequence is " + maxLength);
                return;
            case 8:
                a = "agbcba";// "abcba"
                maxLength = minNumberOfDeletionToMakeAStringAsAPalindromeViaTopDown(a, a.length());
                System.out.println("Min number of deletion in string is " + maxLength);
                return;
            case 9:
                a = "acbcf";
                b = "abcdaf";// "acbcdaf"
                maxStr = printShortestCommonSuperSequenceViaTopDown(a, b, a.length(), b.length());
                System.out.println("print Shortest Common Super Sequence Via Top Down is " + maxStr);
                return;
            case 10:
                a = "aabebcdd";
              //  b = "aabebcdd";// "3"
                maxLength = longestRepeatingSubSequenceViaTopDown(a, a, a.length(), a.length());
                System.out.println("longest Repeating Sub Sequence Via Top Down is " + maxLength);
                return;
            case 11:
                a = "axy";
                b = "adxcpy";// "3"
                boolean isMatching = sequencePatternMatchingViaTopDown(a, b, a.length(), b.length());
                System.out.println("lIs sequence matching " + isMatching);
                return;
            case 12:
                a = "aebcbda";
              //  b = "adxcpy";// "3"
                maxLength = minNumberOfInsertionInAStringToMakeItPalindrome(a, a.length());
                System.out.println("Min number of insertion " + maxLength);
                return;
            default:break;
        }
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
                result += b.charAt(j-1);
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

    public static int lengthOfShortestCommonSuperSequenceViaTopDown(String a, String b, int l1, int l2){
        return l1 + l2 - lcsViaTopDown(a, b, l1, l2);
    }

    public static String minNumberOfInsertionAndDeletionRequiredToConvertOneToOtherStringViaTopDown(String a, String b, int l1, int l2){
        int lcs = lcsViaTopDown(a, b, l1, l2);

        int insertion = l2 - lcs;
        int deletion = l1 - lcs;

        return "insertion count is " + insertion + " and deletion count is " + deletion;
    }

    public static int longestPalindromicSubsequenceViaTopDown(String a, int l1){
        String b = new StringBuilder(a).reverse().toString();
        int l2 = b.length();
        int lcs = lcsViaTopDown(a, b, l1, l2);

        return  lcs;
    }

    public static int minNumberOfDeletionToMakeAStringAsAPalindromeViaTopDown(String a, int l1){
        String b = new StringBuilder(a).reverse().toString();
        int l2 = b.length();
        int lcs = lcsViaTopDown(a, b, l1, l2);

        return  l1 - lcs;
    }

    public static String printShortestCommonSuperSequenceViaTopDown(String a, String b, int l1, int l2){
        lcsViaTopDown(a, b, l1, l2);

        int i = l1;
        int j = l2;
        String result = "";
        while(i > 0 &&  j >0){
            if(a.charAt(i-1) == b.charAt(j-1)){
                result += b.charAt(j-1);
                i--;
                j--;
            }
            else{
                if(mem[i -1][j] > mem[i][j -1]){
                    result += a.charAt(i-1);
                    i--;
                }
                else{
                    result += b.charAt(j-1);
                    j--;
                }
            }
        }

        while(i > 0){
            result += b.charAt(i-1);
            i--;
        }

        while(j >0){
            result += b.charAt(j-1);
            j--;
        }
        int len = result.length();
        if(len > 0){
            result = new StringBuilder(result).reverse().toString();
        }
        return result;
    }

    public static int longestRepeatingSubSequenceViaTopDown(String a, String b, int l1, int l2){
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
                if(a.charAt(i-1) == b.charAt(j-1) && i != j){
                    mem[i][j] = 1 + mem[i-1][j-1];
                }
                else{
                    mem[i][j]  = Math.max(mem[i][j-1], mem[i-1][j]);
                }
            }
        }

        return mem[l1][l2];
    }

    public static boolean sequencePatternMatchingViaTopDown(String a, String b, int l1, int l2){
        int shortLen = l1 > l2 ? l2 : l1;
        int lcsLength = lcsViaTopDown(a,b,l1,l2);

        return shortLen == lcsLength;
    }

    public static int minNumberOfInsertionInAStringToMakeItPalindrome(String a, int l1){
        return minNumberOfDeletionToMakeAStringAsAPalindromeViaTopDown(a, l1);
    }
}
