package com.wordcount;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class WordCountApp extends JFrame {
    private JTextArea textArea;
    private JLabel wordCountLabel;
    private JLabel frequentWordsLabel;
    private JLabel wordLengthLabel;
    private JLabel longestWordLabel;
    private JLabel averageWordLengthLabel;
    private JLabel uniqueWordsLabel;

    private JCheckBox omitStopWordsCheckBox;
    private JCheckBox findLongestWordCheckBox;
    private JCheckBox calculateAverageWordLengthCheckBox;
    private JCheckBox countUniqueWordsCheckBox;

    public WordCountApp() {
        setTitle("Word Count and Text Analysis Tool");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton loadFileButton = new JButton("Load Text File");
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });

        JButton analyzeButton = new JButton("Analyze Text");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analyzeText();
            }
        });

        wordCountLabel = new JLabel("Total Words: ");
        frequentWordsLabel = new JLabel("Most Frequent Words: ");
        wordLengthLabel = new JLabel("Word Lengths: ");
        longestWordLabel = new JLabel("Longest Word: ");
        averageWordLengthLabel = new JLabel("Average Word Length: ");
        uniqueWordsLabel = new JLabel("Unique Words: ");

        omitStopWordsCheckBox = new JCheckBox("Omit Stop Words");
        findLongestWordCheckBox = new JCheckBox("Find Longest Word");
        calculateAverageWordLengthCheckBox = new JCheckBox("Calculate Average Word Length");
        countUniqueWordsCheckBox = new JCheckBox("Count Unique Words");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(loadFileButton);
        panel.add(scrollPane);
        panel.add(analyzeButton);
        panel.add(omitStopWordsCheckBox);
        panel.add(findLongestWordCheckBox);
        panel.add(calculateAverageWordLengthCheckBox);
        panel.add(countUniqueWordsCheckBox);
        panel.add(wordCountLabel);
        panel.add(frequentWordsLabel);
        panel.add(wordLengthLabel);
        panel.add(longestWordLabel);
        panel.add(averageWordLengthLabel);
        panel.add(uniqueWordsLabel);

        add(panel);
        setVisible(true);
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                textArea.setText(content);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void analyzeText() {
        String text = textArea.getText();
        if (text == null || text.isEmpty()) {
            return;
        }

        boolean omitStopWords = omitStopWordsCheckBox.isSelected();
        Map<String, Integer> wordCount = WordCounter.countWords(text, omitStopWords);
        List<Map.Entry<String, Integer>> frequentWords = FrequentWordsFinder.findMostFrequentWords(wordCount, 10);
        Map<Integer, Integer> wordLengths = WordLengthAnalyzer.analyzeWordLengths(text);

        wordCountLabel.setText("Total Words: " + wordCount.size());
        frequentWordsLabel.setText("Most Frequent Words: " + frequentWords.toString());
        wordLengthLabel.setText("Word Lengths: " + wordLengths.toString());

        if (findLongestWordCheckBox.isSelected()) {
            String longestWord = LongestWordFinder.findLongestWord(text);
            longestWordLabel.setText("Longest Word: " + longestWord);
        } else {
            longestWordLabel.setText("Longest Word: N/A");
        }

        if (calculateAverageWordLengthCheckBox.isSelected()) {
            double averageWordLength = AverageWordLengthCalculator.calculateAverageWordLength(text);
            averageWordLengthLabel.setText("Average Word Length: " + averageWordLength);
        } else {
            averageWordLengthLabel.setText("Average Word Length: N/A");
        }

        if (countUniqueWordsCheckBox.isSelected()) {
            int uniqueWordsCount = UniqueWordsCounter.countUniqueWords(text);
            uniqueWordsLabel.setText("Unique Words: " + uniqueWordsCount);
        } else {
            uniqueWordsLabel.setText("Unique Words: N/A");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCountApp();
            }
        });
    }
}
