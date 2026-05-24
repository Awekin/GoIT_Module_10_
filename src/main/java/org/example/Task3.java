package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Task3 {

    public static void main(String[] args) {
        countWordFrequency("words.txt");
    }

    public static void countWordFrequency(String fileName) {
        InputStream inputStream = Task3.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            System.err.println("Файл " + fileName + " не знайдено у resources.");
            return;
        }

        Map<String, Integer> wordCounts = new HashMap<>();
        try (inputStream) {
            byte[] buffer = inputStream.readAllBytes();
            String content = new String(buffer);
            String[] words = content.trim().split("\\s+");
            for (String word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
            return;
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCounts.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}