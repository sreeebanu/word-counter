package com.wordcount;

public class LongestWordFinder {
    public static String findLongestWord(String text) {
        String[] words = text.split("\\W+");
        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        return longestWord;
    }
}

