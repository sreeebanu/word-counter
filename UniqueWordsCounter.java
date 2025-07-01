package com.wordcount;

import java.util.HashSet;
import java.util.Set;

public class UniqueWordsCounter {
    public static int countUniqueWords(String text) {
        String[] words = text.split("\\W+");
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word.toLowerCase());
        }
        return uniqueWords.size();
    }
}

