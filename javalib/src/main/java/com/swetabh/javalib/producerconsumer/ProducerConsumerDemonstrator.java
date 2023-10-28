package com.swetabh.javalib.producerconsumer;

import static com.swetabh.javalib.producerconsumer.ThreadUtil.sleep;
import static com.swetabh.javalib.producerconsumer.ThreadUtil.waitForAllThreadsToComplete;

import java.util.ArrayList;
import java.util.List;

/* Producer thread is responsible to produce items to the Queue, and consumer thread is
 *  responsible to consume items from the Queue.
 *  If Queue is empty then consumer thread will call wait() and entered into waiting state.
 *  After producing items to the queue, producer thread is responsible to call notify method
 *  then waiting consumer will get the notification and continue its execution with updated items
 * */
public class ProducerConsumerDemonstrator {

    // There will be one queue which will handle DataMessages.
    // The Maximum capacity of the queue will be 5.
    private static final int MAX_QUEUE_CAPACITY = 5;

    public static void main(String[] args) {
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);

        // Producer thread, which will produce data in the data queue
        Producer producer = new Producer(dataQueue);
        Thread producerTask = new Thread(producer);

        // Consumer thread which will consume data from the data queue
        Consumer consumer = new Consumer(dataQueue);
        Thread consumerTask = new Thread(consumer);

        // Starting both the threads
        producerTask.start();
        consumerTask.start();

        List<Thread> list = new ArrayList<>();
        list.add(producerTask);
        list.add(consumerTask);

        // Run both the thread for 2 seconds
        sleep(2000);

        // Stop both the threads after 2 seconds of execution
        producer.stop();
        consumer.stop();

        waitForAllThreadsToComplete(list);
    }
}
