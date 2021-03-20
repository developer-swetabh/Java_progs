package com.swetabh.javalib.sequencenumber2;

// In this example we will be printing sequence number using 3 threads
//Thread-1   Thread-2   Thread-3
//  1          6            11
//  2          7            12
//  3          8            13
//  4          9            14
//  5          10           15
public class SequenceNumberGenerator2 {
    private static final int TOTAL_NUMBER_OF_THREADS = 3;
    private static final int TOTAL_NUMBER_TO_PRINT = 15;

    private static int START = 1;

    public static void main(String[] args){

        NumberGenerator2 numberGenerator = new NumberGenerator2(TOTAL_NUMBER_OF_THREADS, TOTAL_NUMBER_TO_PRINT);
        Thread[] threads = new Thread[3];
        threads[0]  = new Thread(new SequenceGeneratorTask2(numberGenerator,START,1),"Thread-1");
        threads[1]  = new Thread(new SequenceGeneratorTask2(numberGenerator,START+5,2),"Thread-2");
        threads[2]  = new Thread(new SequenceGeneratorTask2(numberGenerator,START +10,0),"Thread-3");

        for(Thread th : threads){
            th.start();
        }
    }
}
