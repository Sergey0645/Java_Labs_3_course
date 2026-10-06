package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Task1Main {

    public static void main(String[] args) throws InterruptedException {

        int accountCount = 100;
        int threadCount = 3000;

        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();

        // 1. Створюємо 100 рахунків з випадковою кількістю грошей
        for (int i = 0; i < accountCount; i++) {
            int balance = ThreadLocalRandom.current().nextInt(1000, 10001);
            accounts.add(new Account(i, balance));
        }

        // 2. Підраховуємо загальну суму грошей до переказів
        long totalBefore = 0;

        for (Account account : accounts) {
            totalBefore += account.getBalance();
        }

        System.out.println("Загальна сума до переказів: " + totalBefore);

        // 3. Створюємо 3000 потоків з випадковими переказами
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {

            Thread thread = new Thread(() -> {

                int fromIndex =
                        ThreadLocalRandom.current().nextInt(accountCount);

                int toIndex =
                        ThreadLocalRandom.current().nextInt(accountCount);

                int amount =
                        ThreadLocalRandom.current().nextInt(1, 1001);

                Account from = accounts.get(fromIndex);
                Account to = accounts.get(toIndex);

                bank.transfer(from, to, amount);
            });

            threads.add(thread);
        }

        // Запускаємо всі потоки
        for (Thread thread : threads) {
            thread.start();
        }

        // 4. Чекаємо завершення всіх потоків
        for (Thread thread : threads) {
            thread.join();
        }

        // 5. Підраховуємо загальну суму після переказів
        long totalAfter = 0;

        for (Account account : accounts) {
            totalAfter += account.getBalance();
        }

        System.out.println("Загальна сума після переказів: " + totalAfter);

        // 6. Перевіряємо результат
        if (totalBefore == totalAfter) {
            System.out.println("Перевірка успішна: сума грошей не змінилася.");
        } else {
            System.out.println("Помилка: сума грошей змінилася.");
        }
    }
}