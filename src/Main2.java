import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Клас-модель для зберігання даних одного користувача
class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Гетери потрібні бібліотеці Gson, щоб прочитати поля
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class Task2 {

    public static void main(String[] args) {
        String inputFileName = "file.txt";
        String outputFileName = "user.json";

        List<User> users = new ArrayList<>();

        // 1. ЧИТАННЯ ФАЙЛУ
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line = reader.readLine(); // Читаємо і пропускаємо перший рядок (заголовок)

            // Читаємо решту рядків, поки файл не закінчиться
            while ((line = reader.readLine()) != null) {
                // Розбиваємо рядок на масив слів по пробілу
                String[] parts = line.split(" ");

                // Перевіряємо, чи дійсно є дві колонки, щоб уникнути помилок
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]); // Перетворюємо текст на число

                    // Створюємо об'єкт і додаємо у список
                    users.add(new User(name, age));
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
            return; // Зупиняємо програму, якщо файл не прочитався
        }

        // 2. ЗАПИС У JSON
        // setPrettyPrinting() робить JSON "красивим" (з відступами та перенесеннями рядків)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(outputFileName)) {
            // Метод toJson вміє записувати дані напряму у FileWriter
            gson.toJson(users, writer);
            System.out.println("Файл " + outputFileName + " успішно створено!");
        } catch (IOException e) {
            System.err.println("Помилка під час створення JSON файлу: " + e.getMessage());
        }
    }
}