package com.swetabh.javalib.sequencenumber;

import sun.util.resources.cldr.uk.CalendarData_uk_UA;

// This is the resource which is used by all the 3 threads.
public class NumberGenerator {
    // Number which has to start from
    private int CURRENT_NUMBER = 1;

    // Declaring the number of threads, this has to pass by the client
    private int NUMBER_OF_THREADS;

    // Declaring total number of sequence to generate
    private int TOTAL_NUMBER_OF_SEQUENCE;

    public NumberGenerator(int NUMBER_OF_THREADS, int TOTAL_NUMBER_OF_SEQUENCE) {
        this.NUMBER_OF_THREADS = NUMBER_OF_THREADS;
        this.TOTAL_NUMBER_OF_SEQUENCE = TOTAL_NUMBER_OF_SEQUENCE;
    }

    /*
     * @param : is to specify which thread it is
     * */
    public void printNumber(int CURRENT_THREAD) {
        synchronized (this) {
            while (CURRENT_NUMBER < TOTAL_NUMBER_OF_SEQUENCE -1) {
                while (CURRENT_NUMBER % NUMBER_OF_THREADS != CURRENT_THREAD){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " :: " + CURRENT_NUMBER++);
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
