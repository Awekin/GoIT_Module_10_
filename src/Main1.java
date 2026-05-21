import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Task1 {
    public static void valid(String filePath) throws FileNotFoundException {
        String regex = "[0-9]{3}-[0-9]{3}-[0-9]{4}|\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.matches(regex)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Завдання 1
        Task1.valid("src/file1.txt");
    }
}