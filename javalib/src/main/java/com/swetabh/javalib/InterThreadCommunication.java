package com.swetabh.javalib;

public class InterThreadCommunication {

    /* Two threads can communicate with each other by using wait(), notify() and notifyAll() methods
     * the thread which is expecting updation is responsible to call wait method, then immediately
     * the thread will enter into waiting state.
     * The thread which is responsible to perform updation , after performing updation it is responsible
     * to call notify() then waiting thread will get the notification and continue its execution with
     * those updated items.
     * wait(), notify() and notifyAll() methods are present in Object class not in Thread class.
     * Q. Why these methods are present in object class , but not in thread class. We are not going
     * to use these methods other than thread ref.
     * Ans: because threads can call these methods on any java object.
     * To call wait, notify, notifyAll methods on any object, thread should be owner of that object.
     * i.e the thread should has lock of that object, i.e the thread should be inside synchronized
     * area.
     * Hence we can call wait, notify and notifyAll methods only from synchronized area, otherwise
     * we ll get runtime exception saying "IllegalMonitorStateException"
     * if a thread calls wait() on any object, it immediately releases the lock of that particular
     * object and enter into waiting state.
     * If a thread call notify method on any object, it releases the lock of that object but may not
     * immediately.
     * Except wait, notify and notifyAll, there is no other method where thread releases the lock.
     * Note : Every wait method throws InterruptedException which is checked exception, hence
     * whenever we are using wait method compulsary we should handle this IE, either by try catch
     * or by throws keyword otherwise we ll get compile-time error
     * If you are expecting some updation, calling join method is not recommended.
     *
     * Difference between notify and notifyAll method
     * We can use notify method to give the notification for only one waiting thread. If multiple threads are
     * waiting, then only one thread will be notified and the remaining threads have to wait for further
     * notifications.
     * Which thread will be notified we cant expect, it depends on JVM.
     * We can use notifyAll() to give the notification for all waiting threads of a particular object.
     * Even though multiple threads notified but execution will be performed one by one because threads
     * require lock and only lock is available.
     *
     * Note : On which object we are calling wait(), thread require the lock of that particular
     * object. For example if we are calling wait method on s1, then we have to get lock of
     * s1 object but now s2 object. Ref. diagram on copy below Producer consumer diagram.
     * */
    public static void main(String[] args) throws InterruptedException {

        //In this example main thread excepting updation so main thread call wait()
        ThreadB b = new ThreadB("ThreadB");
        b.start();
        //Lets suppose we call sleep() for 10 sec
        // Thread.sleep(10000);
        // In this case the main thread will wait for 10 sec first, meanwhile the child thread
        // started and completed its execution and even calls the notify. But as main thread is
        // still sleeping it missed the notification. Thus after sleeping main thread will continue
        // to wait forever. This is the worst kind of programming.
        // For this kind of situation we will wait for only particular amount of time. If we
        // do not receive any notification within this time, main thread will continue its execution.
        synchronized (b) {
            System.out.println("Main thread calling wait method");
            b.wait();
            //b.wait(10000); // For the scenario mentioned above.
        }
        System.out.println("MainThread got notification, printing Total = " + b.total);

    }

    static class ThreadB extends Thread {
        public ThreadB(String str) {
            super(str);
        }

        public int total;

        @Override
        public void run() {
            // Child thread is performing updation so after updation , it is giving notification
            synchronized (this) {
                System.out.println("Child thread starts calculation");
                for (int i = 1; i <= 100; i++) {
                    total = total + i;
                }
                System.out.println("Child thread finished calculation and calling notify");
                this.notify();
            }

        }
    }

    /* Here sequence of output will be
        Main thread calling wait method
        Child thread starts calculation
        Child thread finished calculation and calling notify
        MainThread got notification, printing Total = 5050
    */
}
