package com.wordcount;

public class AverageWordLengthCalculator {
    public static double calculateAverageWordLength(String text) {
        String[] words = text.split("\\W+");
        int totalLength = 0;
        for (String word : words) {
            totalLength += word.length();
        }
        return (double) totalLength / words.length;
    }
}

