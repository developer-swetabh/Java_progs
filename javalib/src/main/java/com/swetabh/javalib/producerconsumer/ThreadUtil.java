package com.swetabh.javalib.producerconsumer;

import java.util.List;

// Utility class for thread manipulation
public class ThreadUtil {

    public static void sleep(long interval) {
        try {
            // Wait for some time to demonstrate threads
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForAllThreadsToComplete(List<Thread> threads) {
        for(Thread th: threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
