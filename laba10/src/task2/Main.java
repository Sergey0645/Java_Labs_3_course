package task2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final Logger LOGGER =
            Logger.getLogger(Main.class.getName());

    static {
        try {
            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);

            FileHandler fileHandler =
                    new FileHandler("app.log", true);
            fileHandler.setLevel(Level.FINE);
            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

        } catch (IOException e) {
            System.out.println(
                    "Помилка налаштування логування: "
                            + e.getMessage()
            );
        }
    }

    public static void encryptFile(String inputFile,
                                   String outputFile,
                                   char key) {

        LOGGER.info("Початок шифрування файлу.");
        LOGGER.fine("Вхідний файл: " + inputFile);
        LOGGER.fine("Вихідний файл: " + outputFile);

        try (Reader reader = new FileReader(inputFile);
             Writer writer =
                     new EncryptWriter(new FileWriter(outputFile), key)) {

            LOGGER.fine("Файлові потоки успішно відкрито.");

            char[] buffer = new char[1024];
            int count;

            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }

            System.out.println("Файл успішно зашифровано.");
            LOGGER.info("Файл успішно зашифровано.");

        } catch (IOException e) {

            System.out.println(
                    "Помилка шифрування: " + e.getMessage()
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Помилка під час шифрування файлу.",
                    e
            );
        }
    }

    public static void decryptFile(String inputFile,
                                   String outputFile,
                                   char key) {

        LOGGER.info("Початок дешифрування файлу.");
        LOGGER.fine("Вхідний файл: " + inputFile);
        LOGGER.fine("Вихідний файл: " + outputFile);

        try (Reader reader =
                     new DecryptReader(
                             new FileReader(inputFile), key
                     );
             Writer writer = new FileWriter(outputFile)) {

            LOGGER.fine("Файлові потоки успішно відкрито.");

            char[] buffer = new char[1024];
            int count;

            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }

            System.out.println("Файл успішно розшифровано.");
            LOGGER.info("Файл успішно розшифровано.");

        } catch (IOException e) {

            System.out.println(
                    "Помилка дешифрування: " + e.getMessage()
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Помилка під час дешифрування файлу.",
                    e
            );
        }
    }

    public static void main(String[] args) {

        LOGGER.info("Програму запущено.");
        LOGGER.fine("Тестове повідомлення рівня FINE.");

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
                        System.out.print(
                                "Введіть шлях до вхідного файлу: "
                        );
                        String inputFile = scanner.nextLine();

                        System.out.print(
                                "Введіть шлях та ім'я зашифрованого файлу: "
                        );
                        String encryptedFile = scanner.nextLine();

                        System.out.print(
                                "Введіть ключовий символ: "
                        );
                        String keyInput = scanner.nextLine();

                        if (keyInput.isEmpty()) {

                            System.out.println(
                                    "Помилка: ключовий символ не введено."
                            );

                            LOGGER.warning(
                                    "Ключовий символ не введено."
                            );

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
                                "Введіть шлях до зашифрованого файлу: "
                        );
                        String encryptedInput = scanner.nextLine();

                        System.out.print(
                                "Введіть шлях та ім'я розшифрованого файлу: "
                        );
                        String decryptedFile = scanner.nextLine();

                        System.out.print(
                                "Введіть ключовий символ: "
                        );
                        String decryptKeyInput = scanner.nextLine();

                        if (decryptKeyInput.isEmpty()) {

                            System.out.println(
                                    "Помилка: ключовий символ не введено."
                            );

                            LOGGER.warning(
                                    "Ключовий символ не введено."
                            );

                            break;
                        }

                        char decryptKey =
                                decryptKeyInput.charAt(0);

                        decryptFile(
                                encryptedInput,
                                decryptedFile,
                                decryptKey
                        );

                        break;

                    case 0:
                        System.out.println(
                                "Програму завершено."
                        );

                        LOGGER.info(
                                "Програму завершено."
                        );

                        break;

                    default:
                        System.out.println(
                                "Невірний вибір. Спробуйте ще раз."
                        );

                        LOGGER.warning(
                                "Користувач ввів невірний пункт меню."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Помилка: потрібно ввести число."
                );

                LOGGER.warning(
                        "Замість номера пункту меню введено не число."
                );
            }
        }

        scanner.close();
    }
}