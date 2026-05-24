package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Task2 {

    public static void main(String[] args) {
        String inputFileName = "file2.txt";
        String outputFileName = "user.json";

        List<User> users = new ArrayList<>();

        InputStream inputStream = Task2.class.getClassLoader().getResourceAsStream(inputFileName);
        if (inputStream == null) {
            System.err.println("Помилка: Файл " + inputFileName + " не знайдено у resources.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.readLine(); // пропускаємо заголовок

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    users.add(new User(name, age));
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
            return;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(outputFileName)) {
            gson.toJson(users, writer);
            System.out.println("Файл " + outputFileName + " успішно створено!");
        } catch (IOException e) {
            System.err.println("Помилка під час створення JSON файлу: " + e.getMessage());
        }
    }
}
