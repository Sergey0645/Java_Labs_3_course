package task1;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {

    public static void changeString(String str, String newValue) {
        try {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);

            valueField.set(str, newValue.toCharArray());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Рядок, створений як літерал
        String literalString = "Hello";

        System.out.println("Рядок-літерал до зміни:");
        System.out.println(literalString);

        System.out.print("Введіть нове значення для літерала: ");
        String newLiteralValue = scanner.nextLine();

        changeString(literalString, newLiteralValue);

        System.out.println("Рядок-літерал після зміни:");
        System.out.println(literalString);

        System.out.println();

        // Рядок, введений з клавіатури
        System.out.print("Введіть другий рядок: ");
        String keyboardString = scanner.nextLine();

        System.out.println("Рядок з клавіатури до зміни:");
        System.out.println(keyboardString);

        System.out.print("Введіть нове значення: ");
        String newKeyboardValue = scanner.nextLine();

        changeString(keyboardString, newKeyboardValue);

        System.out.println("Рядок з клавіатури після зміни:");
        System.out.println(keyboardString);

        scanner.close();
    }
}