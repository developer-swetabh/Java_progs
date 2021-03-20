package com.swetabh.javalib.sequencenumber;

// This is the worker which generate the sequence
public class SequenceGeneratorTask implements Runnable{

    private NumberGenerator ng;
    private int currentThread;

    public SequenceGeneratorTask(NumberGenerator ng, int currentThread) {
        this.ng = ng;
        this.currentThread = currentThread;
    }

    @Override
    public void run() {
        ng.printNumber(currentThread);
    }
}
