package task2;

import java.util.concurrent.atomic.AtomicInteger;

public class Task2Main {

    public static void main(String[] args) throws InterruptedException {

        RingBuffer<String> firstBuffer = new RingBuffer<>(10);
        RingBuffer<String> secondBuffer = new RingBuffer<>(10);

        AtomicInteger messageCounter = new AtomicInteger(1);

        // 1. П'ять потоків генерують повідомлення у перший буфер
        for (int i = 1; i <= 5; i++) {

            int producerNumber = i;

            Thread producer = new Thread(() -> {

                while (true) {
                    try {
                        int messageNumber = messageCounter.getAndIncrement();

                        String message =
                                "Потік № " + producerNumber
                                        + " згенерував повідомлення "
                                        + messageNumber;

                        firstBuffer.put(message);

                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });

            producer.setDaemon(true);
            producer.start();
        }

        // 2. Два потоки перекладають повідомлення
        // з першого буфера у другий
        for (int i = 1; i <= 2; i++) {

            int translatorNumber = i;

            Thread translator = new Thread(() -> {

                while (true) {
                    try {
                        String message = firstBuffer.take();

                        int position =
                                message.lastIndexOf("повідомлення ");

                        String messageNumber =
                                message.substring(
                                        position + "повідомлення ".length()
                                );

                        String translatedMessage =
                                "Потік № " + translatorNumber
                                        + " переклав повідомлення "
                                        + messageNumber;

                        secondBuffer.put(translatedMessage);

                    } catch (InterruptedException e) {
                        return;
                    }
                }
            });

            translator.setDaemon(true);
            translator.start();
        }

        // 3. Основний потік читає та друкує 100 повідомлень
        for (int i = 1; i <= 100; i++) {

            String message = secondBuffer.take();

            System.out.println(i + ". " + message);
        }

        System.out.println("Виведено 100 повідомлень.");
    }
}