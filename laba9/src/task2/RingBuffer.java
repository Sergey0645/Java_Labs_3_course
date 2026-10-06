package task2;

public class RingBuffer<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
    }

    private final int slotCount;

    private Node<T> head;
    private Node<T> tail;

    private int headIndex = 0;
    private int tailIndex = 0;

    public RingBuffer(int capacity) {

        // Один додатковий елемент потрібен,
        // щоб відрізняти повний буфер від порожнього
        slotCount = capacity + 1;

        Node<T> first = new Node<>();
        Node<T> current = first;

        for (int i = 1; i < slotCount; i++) {
            current.next = new Node<>();
            current = current.next;
        }

        // Замикаємо список у кільце
        current.next = first;

        head = first;
        tail = first;
    }

    public synchronized void put(T value) throws InterruptedException {

        // Якщо наступна позиція tail є head,
        // буфер повний — чекаємо
        while ((tailIndex + 1) % slotCount == headIndex) {
            wait();
        }

        tail.value = value;
        tail = tail.next;

        tailIndex = (tailIndex + 1) % slotCount;

        notifyAll();
    }

    public synchronized T take() throws InterruptedException {

        // Якщо head і tail співпадають,
        // буфер порожній — чекаємо
        while (headIndex == tailIndex) {
            wait();
        }

        T value = head.value;
        head.value = null;

        head = head.next;

        headIndex = (headIndex + 1) % slotCount;

        notifyAll();

        return value;
    }
}