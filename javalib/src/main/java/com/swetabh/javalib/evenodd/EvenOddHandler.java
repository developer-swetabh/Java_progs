package com.swetabh.javalib.evenodd;

public class EvenOddHandler {

    static class EvenTask implements Runnable{
        private EvenOddGenerator generator;

        public EvenTask(EvenOddGenerator generator) {
            this.generator = generator;
        }
        @Override
        public void run() {
            generator.printEven();
        }
    }

    static class OddTask implements Runnable{
        private EvenOddGenerator generator;

        public OddTask(EvenOddGenerator generator) {
            this.generator = generator;
        }
        @Override
        public void run() {
            generator.printOdd();
        }
    }

    public static void main(String args[]){
        EvenOddGenerator generator = new EvenOddGenerator(15);
        Thread t1 = new Thread(new OddTask(generator), "Thread-1");
        Thread t2 = new Thread(new EvenTask(generator), "Thread-2");

        t1.start();
        t2.start();

    }
}
