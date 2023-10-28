package com.swetabh.javalib.producerconsumer;

public class Consumer implements Runnable {
    private final DataQueue mDataQueue;
    private boolean isRunning = false;

    public Consumer(DataQueue dataQueue) {
        mDataQueue = dataQueue;
    }

    @Override
    public void run() {
        isRunning = true;
        consume();
    }

    private void consume() {
        while (isRunning) {
            if (mDataQueue.isEmpty()) {
                try {
                    mDataQueue.waitIsNotEmpty();
                } catch (InterruptedException e) {
                    System.out.println("Error :: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
            // avoid spurious wake-up
            if (!isRunning) {
                break;
            }
            Message message = mDataQueue.poll();
            printMessage(message);

            //Sleeping on random time to make it realistic
            ThreadUtil.sleep((long) (Math.random() * 100));
        }
        System.out.println("Consumer stopped");
    }

    private void printMessage(Message message) {
        if (message != null) {
            System.out.printf("[%s] Consuming Message. Id: %d, Data: %f%n%n",
                    Thread.currentThread().getName(), message.getId(), message.getData());
        }
    }

    public void stop() {
        isRunning = false;
    }
}
