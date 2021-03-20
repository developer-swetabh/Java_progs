package com.swetabh.javalib.evenodd;

public class EvenOddGenerator {

    private int number = 1;
    private int TOTAL_NUMBER;

    public EvenOddGenerator(int TOTAL_NUMBER) {
        this.TOTAL_NUMBER = TOTAL_NUMBER;
    }

    private boolean isOdd = true;

    public void printOdd() {
        synchronized (this) {
            while (number < TOTAL_NUMBER) {
                if (!isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " :: " + number++);
                isOdd = false;
                notify();
            }
        }
    }

    public void printEven() {
        synchronized (this) {
            while (number < TOTAL_NUMBER) {
                if (isOdd) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " :: " + number++);
                isOdd = true;
                notify();
            }
        }
    }
}
