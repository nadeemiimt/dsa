package com.company.pattern;

public class KnuthMorrisPratAlgorithm {
    public static void call(int i){
        String a = "AABAACAADAABAABA";
        String b = "AABA";
        int maxLength = 0;

        switch (i) {
            case 0:
                kmpAlgorithm(a, b);
                break;
        }
    }

    private static void kmpAlgorithm(String text, String pattern){
        int a = text.length();
        int b = pattern.length();

        int[] patternFoundIndex = new int[a];
        int counter = 0;
        int lps[] = new int[b];

        longestPrefixSuffix(pattern, b, lps);

        int i = 0;
        int j = 0;

        while (i < a){
            if(pattern.charAt(j) == text.charAt(i)){
                j++;
                i++;
            }
            if(j == b){
                patternFoundIndex[counter++] = i - j;
                j = lps[j - 1];
            }

            else if(i < a && pattern.charAt(j) != text.charAt(i)){
                 if(j != 0){
                     j = lps[j-1];
                 }
                 else{
                     i++;
                 }
            }
        }

        while(counter > 0){
            System.out.println("Found pattern "
                    + "at index " + patternFoundIndex[counter--]);
        }
    }

    private static void longestPrefixSuffix(String pattern, int pL, int[] lps){
        int i = 1;
        int j = 0;
        lps[0] = 0;

        while(i < pL){
            if(pattern.charAt(i) == pattern.charAt(j)){
                j++;
                lps[i] = j;
                i++;
            }
            else{
                if(j != 0){
                    j = lps[j - 1];
                }
                else{
                    lps[i] = j;
                    i++;
                }
            }
        }
    }
}
