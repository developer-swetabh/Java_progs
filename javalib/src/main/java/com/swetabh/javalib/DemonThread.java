package com.swetabh.javalib;

/**
 * Threads which are executing in the background are called DaemonThreads.
 * For e.g: Garbage Collector
 *          Attach Listener
 *          Signal Dispatcher
 * The main objective of Daemon Thread is to provide support for non-daemon threads (Main Thread).
 *
 * For e.g if Main thread runs with low memory, then JVM run Garbage Collector to discard
 * useless objects, so that number of bytes of free memory will be improved.
 * With this free memory, main thread can continue its execution.
 *
 * Usually, demon threads having low priority, but based on our requirement demon threads can run
 * with high priority also.
 *
 * We can check demon nature of a thread by using {@code isDaemon()} of thread class.
 * public boolean isDaemon();
 *
 * We can set the Daemon nature of a thread by using {@code setDaemon()} of thread class.
 * public void setDaemon(boolean b);
 * But changing daemon nature is possible before starting of a thread only. After starting a thread
 * if we are trying to change daemon nature then we will get runtime exception saying
 * {@linkplain IllegalThreadStateException}
 *
 * Default Nature: By default main thread is always non-daemon and for all remaining threads will
 * be inherited from parent to child i.e if the parent thread is daemon then automatically child
 * thread is also daemon.
 *
 * Note: It is impossible to change daemon nature of main thread because it is already started by
 * JVM at beginning.
 *
 * Whenever last non daemon thread terminates, automatically all daemon threads will be terminated
 * irrespective of their position.
 * */
public class DemonThread {

    static class MyThread extends Thread {

        @Override
        public void run() {
        }
    }

    static class MyThread2 extends Thread {

        @Override
        public void run() {
            for(int i =0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + " :: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void main(String[] args) {
        // Check if Main Thread is daemon or not
        System.out.println("Is Main Thread daemon = " +Thread.currentThread().isDaemon());
        //Thread.currentThread().setDaemon(true); // Here will get IllegalThreadStateException

        MyThread thread = new MyThread();
        System.out.println("Is Child Thread daemon = " +thread.isDaemon());
        thread.setDaemon(true);
        System.out.println("Setting daemon nature to Child Thread");
        System.out.println("Is Child Thread daemon = " +thread.isDaemon());

        MyThread2 th = new MyThread2();
        // If we comment this line, then both main thread and child thread will continue to end
        th.setDaemon(true);
        th.start();
        // But if the above line is there means, once main thread completes child thread will
        // terminates.
        System.out.println("Main thread ended");
    }
}
