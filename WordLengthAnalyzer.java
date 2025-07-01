package com.wordcount;

import java.util.*;

public class WordLengthAnalyzer {
    public static Map<Integer, Integer> analyzeWordLengths(String text) {
        Map<Integer, Integer> lengthCount = new HashMap<>();
        String[] words = text.split("\\W+");
        for (String word : words) {
            int length = word.length();
            lengthCount.put(length, lengthCount.getOrDefault(length, 0) + 1);
        }
        return lengthCount;
    }
}
