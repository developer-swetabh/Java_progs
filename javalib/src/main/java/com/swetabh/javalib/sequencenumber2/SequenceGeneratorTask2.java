package com.swetabh.javalib.sequencenumber2;

public class SequenceGeneratorTask2 implements Runnable {
    private int START;
    private int THREAD_NO;
    private NumberGenerator2 mNumberGenerator;
    public SequenceGeneratorTask2(NumberGenerator2 numberGenerator,int start, int threadNo) {
        this.START = start;
        this.THREAD_NO = threadNo;
        this.mNumberGenerator = numberGenerator;
    }

    @Override
    public void run() {
        mNumberGenerator.printNumber(START,THREAD_NO);
    }
}
