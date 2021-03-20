package com.swetabh.javalib.sequencenumber;

// In this example we will be printing sequence number using 3 threads
//Thread-1   Thread-2   Thread-3
//  1          2            3
//  4          5            6
//  7          8            9
//  10         11           12
public class MultiThreading {
    public static void main(String[] args) {
        NumberGenerator ng = new NumberGenerator(3, 12);
        Thread[] thArray = new Thread[3];
        thArray[0] = new Thread(new SequenceGeneratorTask(ng,1),"Thread 1");
        thArray[1] = new Thread(new SequenceGeneratorTask(ng,2),"Thread 2");
        thArray[2] = new Thread(new SequenceGeneratorTask(ng,0),"Thread 3");

        for(Thread th : thArray){
            th.start();
        }

    }
}
