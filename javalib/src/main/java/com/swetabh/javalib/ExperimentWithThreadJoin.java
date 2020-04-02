package com.swetabh.javalib;

public class ExperimentWithThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from experiment with Thread ");

        /* If a thread want to complete after completion of some other thread then we should go for join method.
         * Lets assume there are two thread T1 and T2.
         * If T1 wants to wait till completion of T2 then T1 has to call join method on T2 object.
         */

        //lets start the main thread after completion of child thread
        //Uncomment to try this
        /*Thread1 thread1 = new Thread1();
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i =0;i<10;i++){
            System.out.println("Main thread loop no #" + i);
        }
        */


        //Lets start the child thread after completion of the main thread
        Thread2.mainThread = Thread.currentThread();
        Thread2 thread2 = new Thread2();
        thread2.start();
        /* this is the case where main thread will be waiting for child thread completion
         * and child thread will be waiting for main thread completion
         * this will cause a deadlock situation.
         * thread2.join();
         */
        for (int i = 0; i < 10; i++) {
            System.out.println("Main thread loop no #" + i);
        }

        /* If a thread don't want to perform any operation for a
         * certain period of time then we should go for
         * Thread.sleep()
         * The interrupt() will interrupt the child thread which is in sleeping/waiting state
         * If the thread is not in sleeping state and it is executing normally and we call interrupt()
         * then nothing will happen, we won't get @{InterruptedException}.
         * But the interrupt call be waiting to interrupt the child thread when it goes to sleep/waiting
         * state
         * The JVM will handle the interruption. Main thread won't wait, it will continue
         * */
        Thread3 thread3 = new Thread3();
        thread3.start();
        thread3.interrupt();
        System.out.println("End of Main thread");

    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Child Thread 1 loop no #" + i);
            }
        }
    }

    static class Thread2 extends Thread {

        private static Thread mainThread;

        @Override
        public void run() {
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("Child Thread 2 loop no #" + i);
            }
        }
    }

    static class Thread3 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Child Thread 3 loop no #" + i);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Got interrupted");
            }
        }
    }

}
