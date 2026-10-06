package task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final Logger LOGGER =
            Logger.getLogger(Main.class.getName());

    private static ResourceBundle bundle =
            ResourceBundle.getBundle(
                    "location.messages",
                    new Locale("uk")
            );

    static {
        try {
            LOGGER.setUseParentHandlers(false);
            LOGGER.setLevel(Level.ALL);

            ConsoleHandler consoleHandler =
                    new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);

            FileHandler fileHandler =
                    new FileHandler("app.log", true);
            fileHandler.setLevel(Level.FINE);
            fileHandler.setFormatter(
                    new SimpleFormatter()
            );

            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

        } catch (IOException e) {
            System.out.println(
                    "Помилка налаштування логування: "
                            + e.getMessage()
            );
        }
    }

    public static void encryptFile(
            String inputFile,
            String outputFile,
            char key
    ) {

        LOGGER.info("Початок шифрування файлу.");
        LOGGER.fine("Вхідний файл: " + inputFile);
        LOGGER.fine("Вихідний файл: " + outputFile);

        try (
                Reader reader =
                        new FileReader(inputFile);

                Writer writer =
                        new EncryptWriter(
                                new FileWriter(outputFile),
                                key
                        )
        ) {

            LOGGER.fine(
                    "Файлові потоки успішно відкрито."
            );

            char[] buffer = new char[1024];
            int count;

            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }

            System.out.println(
                    bundle.getString("message.encrypted")
            );

            LOGGER.info(
                    "Файл успішно зашифровано."
            );

        } catch (IOException e) {

            System.out.println(
                    bundle.getString("error.encrypt")
                            + e.getMessage()
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Помилка під час шифрування файлу.",
                    e
            );
        }
    }

    public static void decryptFile(
            String inputFile,
            String outputFile,
            char key
    ) {

        LOGGER.info("Початок дешифрування файлу.");
        LOGGER.fine("Вхідний файл: " + inputFile);
        LOGGER.fine("Вихідний файл: " + outputFile);

        try (
                Reader reader =
                        new DecryptReader(
                                new FileReader(inputFile),
                                key
                        );

                Writer writer =
                        new FileWriter(outputFile)
        ) {

            LOGGER.fine(
                    "Файлові потоки успішно відкрито."
            );

            char[] buffer = new char[1024];
            int count;

            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }

            System.out.println(
                    bundle.getString("message.decrypted")
            );

            LOGGER.info(
                    "Файл успішно розшифровано."
            );

        } catch (IOException e) {

            System.out.println(
                    bundle.getString("error.decrypt")
                            + e.getMessage()
            );

            LOGGER.log(
                    Level.SEVERE,
                    "Помилка під час дешифрування файлу.",
                    e
            );
        }
    }

    public static void changeLanguage(
            Scanner scanner
    ) {

        System.out.println();
        System.out.println(
                bundle.getString("language.title")
        );

        System.out.println(
                bundle.getString("language.uk")
        );

        System.out.println(
                bundle.getString("language.en")
        );

        System.out.print(
                bundle.getString("prompt.choice")
        );

        String languageChoice =
                scanner.nextLine();

        switch (languageChoice) {

            case "1":

                bundle =
                        ResourceBundle.getBundle(
                                "location.messages",
                                new Locale("uk")
                        );

                System.out.println(
                        bundle.getString(
                                "language.changed"
                        )
                );

                LOGGER.info(
                        "Вибрано українську мову."
                );

                break;

            case "2":

                bundle =
                        ResourceBundle.getBundle(
                                "location.messages",
                                Locale.ENGLISH
                        );

                System.out.println(
                        bundle.getString(
                                "language.changed"
                        )
                );

                LOGGER.info(
                        "Вибрано англійську мову."
                );

                break;

            default:

                System.out.println(
                        bundle.getString(
                                "message.invalidChoice"
                        )
                );

                LOGGER.warning(
                        "Невірний вибір мови."
                );
        }
    }

    public static void main(String[] args) {

        LOGGER.info("Програму запущено.");
        LOGGER.fine(
                "Тестове повідомлення рівня FINE."
        );

        Scanner scanner =
                new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {

            System.out.println();

            System.out.println(
                    "===== "
                            + bundle.getString(
                            "menu.title"
                    )
                            + " ====="
            );

            System.out.println(
                    bundle.getString(
                            "menu.encrypt"
                    )
            );

            System.out.println(
                    bundle.getString(
                            "menu.decrypt"
                    )
            );

            System.out.println(
                    bundle.getString(
                            "menu.language"
                    )
            );

            System.out.println(
                    bundle.getString(
                            "menu.exit"
                    )
            );

            System.out.print(
                    bundle.getString(
                            "prompt.choice"
                    )
            );

            try {

                choice =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

                switch (choice) {

                    case 1:

                        System.out.print(
                                bundle.getString(
                                        "prompt.inputFile"
                                )
                        );

                        String inputFile =
                                scanner.nextLine();

                        System.out.print(
                                bundle.getString(
                                        "prompt.encryptedFile"
                                )
                        );

                        String encryptedFile =
                                scanner.nextLine();

                        System.out.print(
                                bundle.getString(
                                        "prompt.key"
                                )
                        );

                        String keyInput =
                                scanner.nextLine();

                        if (keyInput.isEmpty()) {

                            System.out.println(
                                    bundle.getString(
                                            "error.keyEmpty"
                                    )
                            );

                            LOGGER.warning(
                                    "Ключовий символ не введено."
                            );

                            break;
                        }

                        char encryptKey =
                                keyInput.charAt(0);

                        encryptFile(
                                inputFile,
                                encryptedFile,
                                encryptKey
                        );

                        break;

                    case 2:

                        System.out.print(
                                bundle.getString(
                                        "prompt.encryptedInput"
                                )
                        );

                        String encryptedInput =
                                scanner.nextLine();

                        System.out.print(
                                bundle.getString(
                                        "prompt.decryptedFile"
                                )
                        );

                        String decryptedFile =
                                scanner.nextLine();

                        System.out.print(
                                bundle.getString(
                                        "prompt.key"
                                )
                        );

                        String decryptKeyInput =
                                scanner.nextLine();

                        if (decryptKeyInput.isEmpty()) {

                            System.out.println(
                                    bundle.getString(
                                            "error.keyEmpty"
                                    )
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

                    case 3:

                        changeLanguage(scanner);
                        break;

                    case 0:

                        System.out.println(
                                bundle.getString(
                                        "message.finished"
                                )
                        );

                        LOGGER.info(
                                "Програму завершено."
                        );

                        break;

                    default:

                        System.out.println(
                                bundle.getString(
                                        "message.invalidChoice"
                                )
                        );

                        LOGGER.warning(
                                "Користувач ввів невірний пункт меню."
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        bundle.getString(
                                "error.number"
                        )
                );

                LOGGER.warning(
                        "Замість номера пункту меню введено не число."
                );
            }
        }

        scanner.close();
    }
}