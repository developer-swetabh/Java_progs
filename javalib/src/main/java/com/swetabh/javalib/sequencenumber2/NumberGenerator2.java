package com.swetabh.javalib.sequencenumber2;

public class NumberGenerator2 {
    private final int TOTAL_NUMBER_OF_THREADS;
    private final int TOTAL_NUMBER_TO_PRINT;
    private int currentNumber = 1;

    public NumberGenerator2(int numberOfThreads, int numberToPrint) {
        this.TOTAL_NUMBER_OF_THREADS = numberOfThreads;
        this.TOTAL_NUMBER_TO_PRINT = numberToPrint;
    }

    public void printNumber(int start, int thread) {

        synchronized (this) {
            while (currentNumber < TOTAL_NUMBER_TO_PRINT -1) {
                while (currentNumber % TOTAL_NUMBER_OF_THREADS != thread){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " :: start = " + start++);
                currentNumber++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyAll();
            }

        }
    }
}
