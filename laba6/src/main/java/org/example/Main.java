package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Translator translator = new Translator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("good", "добрий");
        translator.addWord("morning", "ранок");
        translator.addWord("student", "студент");
        translator.addWord("computer", "комп'ютер");

        System.out.print("How many words do you want to add? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {

            System.out.print("English word: ");
            String english = scanner.nextLine();

            System.out.print("Ukrainian word: ");
            String ukrainian = scanner.nextLine();

            translator.addWord(english, ukrainian);
        }

        System.out.print("Enter English phrase: ");
        String phrase = scanner.nextLine();

        String result = translator.translate(phrase);

        System.out.println("Translation: " + result);

        scanner.close();
    }
}