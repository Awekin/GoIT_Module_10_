package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Task3 {

    public static void main(String[] args) {
        countWordFrequency("words.txt");
    }

    public static void countWordFrequency(String fileName) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Файл " + fileName + " не знайдено.");
            return;
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCountMap.entrySet());

        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
