package com.swetabh.javalib;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Problems with Traditional synchronized keyword.
 * 1. We are not having any flexibility to try for a lock without waiting.
 * 2. There is no way to specify maximum waiting time for a thread to get lock, so that thread
 * will wait until getting the lock which may create performance problem, which may cause
 * DeadLock.
 * 3. If a thread releases a lock then which waiting thread will get the lock we are not having
 * any control of this.
 * 4. There is no API to list out all waiting threads for a lock.
 * The Synchronization keyword compulsory we have to use, either at method level or within a method
 * and it is not possible to use across multiple methods.
 * <p>
 * To overcome these problems, SUN introduced java.uti.concurrent.locks package in 1.5version.
 * It also provides several enhancements to the programmer to provide more control on concurrency.
 * <p>
 * {@link java.util.concurrent.locks.Lock} interface:
 * Lock object is similar to implicit lock acquired by a thread to execute synchronized method or
 * synchronized block.
 * <p>
 * Lock implementation provided more extensive operation than traditional implicit locks.
 * <p>
 * Important methods of {@link java.util.concurrent.locks.Lock} interface
 * void {@code lock()}: we can use this method to acquire a lock. If the lock is already available then
 * immediately current thread will get that lock. If the lock is not already available then
 * it will wait until getting the lock. It is exactly same behavior of traditional synchronized
 * keyword
 * <p>
 * boolean {@code tryLock()}: To acquire the lock without waiting.
 * If the lock is available then thread acquire the lock and returns true. If the lock is not available
 * then this method returns false and again continue its execution without waiting. In this case
 * thread never be entered into waiting state.
 * if(l.tryLock() {
 * // perform safe operation;
 * } else {
 * // perform alternative operation
 * }
 * <p>
 * boolean {@code tryLock(long time, TimeUnit unit)} : If lock is available then thread will get
 * the lock and continue its execution. If the lock is not available, then the thread will wait
 * until specified amount of time, still if the lock is not available then thread can continue its
 * execution.
 * TimeUnit is an enum present in java.util.concurrent package
 * if (l.tryLock(1000, TimeUnit.MILLISECONDS)) {
 * // perform opearation
 * }
 * <p>
 * void {@code lockInterruptibly} : Acquires the lock if it is available and returns immediately.
 * If the lock is not available then it will wait, while waiting if the thread is interrupted then
 * thread wont get the lock.
 * <p>
 * void {@code unlock()} : To releases the lock
 * To call this method compulsory, current thread should be owner of the lock otherwise we will get
 * runtime exception saying {@link IllegalMonitorStateException}
 * <p>
 * {@linkplain java.util.concurrent.locks.ReentrantLock}
 * It is the implementation class of lock interface and it is direct child class of Object.
 * Reentrant means a thread can acquire same lock multiple times without any issue.
 * Internally, the Reentrant lock increment thread personal count whenever we call {@code lock()} method
 * and decrement count value whenever thread calls unlock() method and lock will be released
 * whenever count reaches 0.
 * <p>
 * Important methods of {@link ReentrantLock}
 * void lock()
 * boolean tryLock()
 * boolean tryLock(long l, TimeUnits t)
 * void lockInterruptibly()
 * void unlock()
 * int getHoldCount() : returns number of hold count by current thread
 * boolean isHeldByCurrentThread() : returns true if and only if lock is hold by current thread
 * int getQueueLength() : returns number of threads waiting for the lock
 * Collection getQueuedThreads() : returns a collection of thread which are waiting to get the lock.
 * boolean hasQueuedThread() : returns true if any thread waiting to get the lock.
 * boolean isLocked() : returns true if the lock is acquired by some thread.
 * boolean isFair() : returns true if the fairness policy is set with true value.
 * Thread getOwner() : returns the thread which acquires the lock.
 */
public class ExperimentWithConcurrentPackage {

    public static void main(String[] args) {
        // Different constructors
        //1. Create an instance of Reentrant lock
        ReentrantLock lock = new ReentrantLock();

        // 2. Constructor with fairness
        // Create a Reentrant lock with given fairness policy.
        // If the fairness is true then longest waiting thread can acquire the lock if it is
        // available i.e it follows First come First Serve policy.
        // If fairness is false, then which waiting thread will get the chance we cant expect.
        // The default value of fairness is false.
        //ReentrantLock lock2 = new ReentrantLock(boolean fairness)

        // Example 1
        Display d = new Display();
        ThreadA th1 = new ThreadA(d, "Dhoni");
        ThreadA th2 = new ThreadA(d, "Yuvraj");

        System.out.print("Starting both threads from Main Thread");
        th1.start();
        th2.start();

        // Without synchronization output will come something like this
        /*
        * GoodMorning: Dhoni
          GoodMorning: Yuvraj
          GoodMorning: Yuvraj
          GoodMorning: Dhoni
          GoodMorning: Dhoni
          GoodMorning: Yuvraj
        * */

        // By making wish() method synchronized, output will come like this: -----> (2)
        /*
        * GoodMorning: Dhoni
          GoodMorning: Dhoni
          GoodMorning: Dhoni
          GoodMorning: Dhoni
          GoodMorning: Yuvraj
          GoodMorning: Yuvraj
          GoodMorning: Yuvraj
          GoodMorning: Yuvraj
        * */

        // Example 2, Checking with tryLock method
        ThreadB thb1 = new ThreadB("First thread");
        ThreadB thb2 = new ThreadB("Second thread");

        thb1.start();
        thb2.start();

        // Example 3, tryLock with time period
        ThreadC thc1 = new ThreadC("First thread");
        ThreadC thc2 = new ThreadC("Second thread");

        thc1.start();
        thc2.start();

    }

    public static class Display {
        //public synchronized void wish(String name) { //-------> (2)

        /**
         * Now, we will achieve the same output as (2) this time we will use {@link ReentrantLock}
         */
        ReentrantLock lock = new ReentrantLock();

        public void wish(String name) {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.print("GoodMorning: ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
            }
            lock.unlock();
        }
    }

    public static class ThreadA extends Thread {
        Display mDisplay;
        String mName;

        public ThreadA(Display display, String name) {
            mDisplay = display;
            mName = name;
        }

        @Override
        public void run() {
            mDisplay.wish(mName);
        }
    }

    public static class ThreadB extends Thread {
        public static ReentrantLock lock = new ReentrantLock();

        public ThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {
            if (lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + "....." + " got the lock and performing safe operation");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            } else {
                System.out.println(Thread.currentThread().getName() + "....." + " did not got the lock and performing alternate operation");
            }
        }
    }

    private static class ThreadC extends Thread {
        static ReentrantLock lock = new ReentrantLock();

        public ThreadC(String name) {
            super(name);
        }

        @Override
        public void run() {
            do {
                try {
                    if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName()
                                + "....."
                                + " got the lock and performing safe operation");
                        Thread.sleep(10000);
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName()
                                + "......"
                                + " releases the lock");
                        // Come out of do while loop
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName()
                                + "....."
                                + " will try again to get the lock");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (true);
        }
    }
}
