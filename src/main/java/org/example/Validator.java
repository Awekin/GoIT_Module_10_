package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class Validator {
    public static void valid(String fileName) {
        String regex = "[0-9]{3}-[0-9]{3}-[0-9]{4}|\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}";

        InputStream inputStream = Validator.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            System.err.println("Помилка: Файл " + fileName + " не знайдено у resources.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(regex)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
        }
    }
}
