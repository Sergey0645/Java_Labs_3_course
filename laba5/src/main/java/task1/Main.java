package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть шлях до файлу: ");
        String fileName = scanner.nextLine();

        String maxLine = "";
        int maxWords = 0;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String trimmedLine = line.trim();

                if (!trimmedLine.isEmpty()) {

                    String[] words = trimmedLine.split("\\s+");

                    if (words.length > maxWords) {
                        maxWords = words.length;
                        maxLine = line;
                    }
                }
            }

            System.out.println();
            System.out.println("Рядок з максимальною кількістю слів:");
            System.out.println(maxLine);
            System.out.println("Кількість слів: " + maxWords);

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }
}