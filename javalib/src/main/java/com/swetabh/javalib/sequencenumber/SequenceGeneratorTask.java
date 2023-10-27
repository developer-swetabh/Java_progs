package com.swetabh.javalib.sequencenumber;

// This is the worker which generate the sequence
public class SequenceGeneratorTask implements Runnable{

    private final NumberGenerator ng;
    private final int currentThread;

    public SequenceGeneratorTask(NumberGenerator ng, int currentThread) {
        this.ng = ng;
        this.currentThread = currentThread;
    }

    @Override
    public void run() {
        ng.printNumber(currentThread);
    }
}
