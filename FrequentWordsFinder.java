package com.wordcount;

import java.util.*;

public class FrequentWordsFinder {
    public static List<Map.Entry<String, Integer>> findMostFrequentWords(Map<String, Integer> wordCount, int topN) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        return list.subList(0, Math.min(topN, list.size()));
    }
}
