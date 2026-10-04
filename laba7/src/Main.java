import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static boolean isPerfect(int number) {
        int sum = IntStream.range(1, number)
                .filter(i -> number % i == 0)
                .sum();

        return sum == number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть n: ");
        int n = scanner.nextInt();

        System.out.println("Досконалі числа від 1 до " + n + ":");

        IntStream.rangeClosed(1, n)
                .filter(number -> isPerfect(number))
                .forEach(number -> System.out.println(number));

        scanner.close();
    }
}