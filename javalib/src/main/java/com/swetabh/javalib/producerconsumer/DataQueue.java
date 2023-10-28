package com.swetabh.javalib.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class DataQueue {
    // Maximum capacity of the DataQueue.
    private final int MAX_QUEUE_CAPACITY;
    private Queue<Message> queue = new LinkedList<>();

    /**
     * This lock will be used by producer threads.
     * If multiple producer thread tries to produce at a time.
     * Only one thread should get the chance.
     */
    private Object IS_NOT_FULL = new Object();

    /**
     * This lock will be used by consumer threads.
     * If multiple consumer thread tries to consume at a time.
     * Only one thread should get the chance.
     */
    private Object IS_NOT_EMPTY = new Object();

    public DataQueue(int maxQueueCapacity) {
        MAX_QUEUE_CAPACITY = maxQueueCapacity;
    }

    public boolean isFull() {
        return queue.size() == MAX_QUEUE_CAPACITY;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void waitIsNotFull() throws InterruptedException {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.wait();
        }
    }

    public void waitIsNotEmpty() throws InterruptedException {
        synchronized (IS_NOT_EMPTY) {
            IS_NOT_EMPTY.wait();
        }
    }

    public void notifyIsNotEmpty() {
        synchronized (IS_NOT_EMPTY) {
            IS_NOT_EMPTY.notify();
        }
    }

    public void notifyIsNotFull() {
        synchronized (IS_NOT_FULL) {
            IS_NOT_FULL.notify();
        }
    }

    public void add(Message message) {
        queue.add(message);
        // Notifying the consumer thread, they can now able to consume the message.
        notifyIsNotEmpty();
    }

    public Message poll() {
        Message message = queue.poll();
        // Notifying the producer thread, queue is not full now, they can produce the message.
        notifyIsNotFull();
        return message;
    }

    public int getSize() {
        return queue.size();
    }
}
