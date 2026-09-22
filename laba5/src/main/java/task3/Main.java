package task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class Main {

    public static void encryptFile(String inputFile,
                                   String outputFile,
                                   char key) {

        try (Reader reader = new FileReader(inputFile);
             Writer writer =
                     new EncryptWriter(new FileWriter(outputFile), key)) {

            char[] buffer = new char[1024];
            int count;

            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }

            System.out.println("Файл успішно зашифровано.");

        } catch (IOException e) {
            System.out.println("Помилка шифрування: "
                    + e.getMessage());
        }
    }

    public static void decryptFile(String inputFile,
                                   String outputFile,
                                   char key) {

        try (Reader reader =
                     new DecryptReader(new FileReader(inputFile), key);
             Writer writer = new FileWriter(outputFile)) {

            char[] buffer = new char[1024];
            int count;

            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }

            System.out.println("Файл успішно розшифровано.");

        } catch (IOException e) {
            System.out.println("Помилка дешифрування: "
                    + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {

            System.out.println();
            System.out.println("===== МЕНЮ =====");
            System.out.println("1 - Зашифрувати файл");
            System.out.println("2 - Розшифрувати файл");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Введіть шлях до вхідного файлу: ");
                        String inputFile = scanner.nextLine();

                        System.out.print(
                                "Введіть шлях та ім'я зашифрованого файлу: ");
                        String encryptedFile = scanner.nextLine();

                        System.out.print("Введіть ключовий символ: ");
                        String keyInput = scanner.nextLine();

                        if (keyInput.isEmpty()) {
                            System.out.println(
                                    "Помилка: ключовий символ не введено.");
                            break;
                        }

                        char encryptKey = keyInput.charAt(0);

                        encryptFile(
                                inputFile,
                                encryptedFile,
                                encryptKey
                        );

                        break;

                    case 2:
                        System.out.print(
                                "Введіть шлях до зашифрованого файлу: ");
                        String encryptedInput = scanner.nextLine();

                        System.out.print(
                                "Введіть шлях та ім'я розшифрованого файлу: ");
                        String decryptedFile = scanner.nextLine();

                        System.out.print("Введіть ключовий символ: ");
                        String decryptKeyInput = scanner.nextLine();

                        if (decryptKeyInput.isEmpty()) {
                            System.out.println(
                                    "Помилка: ключовий символ не введено.");
                            break;
                        }

                        char decryptKey = decryptKeyInput.charAt(0);

                        decryptFile(
                                encryptedInput,
                                decryptedFile,
                                decryptKey
                        );

                        break;

                    case 0:
                        System.out.println("Програму завершено.");
                        break;

                    default:
                        System.out.println(
                                "Невірний вибір. Спробуйте ще раз.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Помилка: потрібно ввести число.");
            }
        }

        scanner.close();
    }
}