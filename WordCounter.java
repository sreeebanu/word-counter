package com.wordcount;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

public class WordCounter {
    private static Set<String> stopWords = new HashSet<>();

    static {
        try {
            List<String> lines = Files.readAllLines(Paths.get("stopwords.txt"));
            stopWords.addAll(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> countWords(String text, boolean omitStopWords) {
        Map<String, Integer> wordCount = new HashMap<>();
        String[] words = text.split("\\W+");
        for (String word : words) {
            word = word.toLowerCase();
            if (!omitStopWords || !stopWords.contains(word)) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }
}

