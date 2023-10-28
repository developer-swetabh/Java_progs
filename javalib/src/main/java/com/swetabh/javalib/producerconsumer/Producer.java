package com.swetabh.javalib.producerconsumer;

import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private final DataQueue mDataQueue;
    private final AtomicInteger id = new AtomicInteger(0);
    private boolean isRunning = false;

    public Producer(DataQueue dataQueue) {
        mDataQueue = dataQueue;
    }

    @Override
    public void run() {
        isRunning = true;
        produce();
    }

    private void produce() {
        while (isRunning){
            if (mDataQueue.isFull()) {
                try {
                    mDataQueue.waitIsNotFull();
                } catch (InterruptedException e) {
                    System.out.println("Error :: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }

            // avoid spurious wake-up
            if (!isRunning) {
                break;
            }
            Message message = new Message(id.incrementAndGet(),Math.random());
            mDataQueue.add(message);
            printMessage(message);
            System.out.println("DataQueue size = " + mDataQueue.getSize());

            //Sleeping on random time to make it realistic
            ThreadUtil.sleep((long) (Math.random() * 100));
        }
        System.out.println("Producer stopped");
    }

    private void printMessage(Message message) {
        if (message != null) {
            System.out.printf("[%s] Producing Message. Id: %d, Data: %f%n%n",
                    Thread.currentThread().getName(), message.getId(), message.getData());
        }
    }

    public void stop(){
        isRunning = false;
    }
}
