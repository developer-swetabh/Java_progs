package com.swetabh.javalib;

public class ExperimentWithSynchronization {

    /* Synchronized keyword is a modifier, applicable for method, class but not for variables.
     * Problem with the synchronized is , it increases the waiting time of thread
     * and create performance problem
     * If there is no specific requirement then it is not recommended to use Synchronized
     * keyword
     * Internally this synchronization concept is implemented using lock concept
     * Every object in JAVA has a unique lock
     * Once Synchronization method execution complete, thread releases the lock
     * Acquiring and releasing the lock will be performed automatically by JVM
     * If a thread wants to execute , synchronized method on the given object, first it has to get
     * lock of that object. Once thread gets the lock, then it is allowed to execute any synchronized
     * method on that object.
     * Once method execution completes , automatically thread releases the lock.
     * Lets take an example of Class X having different methods
     * Class X {
     *     @synchronized m1(){}
     *
     *     @synchronized m2(){}
     *
     *     m3(){}
     * }
     * Now T1 thread comes and want to access m1(). It can happily access it.
     * Another thread T2 comes and want to access m1(). Will be allowed to access ?
     * Ans :  No, it wont be allowed as a lock of object has been occupied by T1
     * Suppose another thread T3 comes and want to access m2(). Will it be allowed to access ?
     * Ans : No, it wont be allowed as lock is on the object. Untill the lock has been released
     * thread is not allowed to access any synchronized method
     * If suppose thread T4 wants to access m3(). Will it be allowed ?
     * Ans : Yes , it will be as there is no lock
     * While a thread executing a synchronized method on a given object, the remaining threads
     * are not allowed to execute any synchronized method simultaneously on the same object.
     * Remaining threads are allowed to execute non synchronized methods simultaneously.
     * In JAVA we have two levels of lock. 1. Class level lock and 2. Object level lock
     * Class level lock :
     * Every class in java has a unique lock which is also known as class level lock
     * If a thread wants to execute {static synchronized} method, then thread required class level lock.
     * Once thread got class level lock, then it is allowed to execute any static synchronized method
     * of that class. Once method execution completes, automatically thread releases the lock.
     * While a thread executing static synchronized method,the remaining threads are not allowed to execute
     * any static synchronized method of that class simultaneously.But remaining threads are allowed to
     * execute the following methods simultaneously
     * 1. Normal static methods, 2. synchronized instance methods, 3. Normal instance methods
     * Lets suppose I have a class x with following methods
     * class x {
     *     static sync m1(){}
     *     static sync m2(){}
     *     static m3(){}
     *     sync m4(){}
     *     m5(){}
     * }
     * Now T1 thread comes and wants to access m1(), it is allowed to access. Soon after T2 thread
     * comes and wants to access m2(). T2 will not get a chance to execute and will go to waiting state
     * as m2() requires class level lock. T3 thread comes and wants to access m3(), it will be allowed
     * to execute. Similarly T4 wants to execute m4(), it will also be allowed to execute, as it requires
     * object level lock. Same T5 wants to execute m5(), it will also be allowed.
     * Now lets suppose Hydrabad to vijaywada 4 lane highway is there.It require 4 hr to reach vijaywada
     * from hydrabad. In between the route there is a very narrow lane where only one vehicle is allowed
     * to pass. If we make the complete lane as synchronized then only one vehicle will allowed to pass.
     * So in 24 hrs only 6 vehicles will be allowed. This type of programming will be the worst kind of
     * programming
     *  Hyd                                                                             Vijayawada
     * ------------------------------------             -------------------------------------------
     *                                      Only one vehicle is allowed to pass
     *                                      ------------
     *                                      ------------
     *
     * -------------------------------------            ------------------------------------------
     * So instead of making the complete method as synchronized, we will make that bridge only to be
     * synchronized.
     * If very few lines of code is required synchronization, then it is not recommended to declare
     * entire method as synchronized. We have to enclose those few lines of code by using synchronized
     * block.
     * The main advantage of synchronized block over synchronized method is , it reduces waiting time
     * of threads and improves performance of the system.
     * We can declare synchronized block as follows
     * 1. To get lock of current object :
     * -------------------------------
     * synchronized(this){
     *
     *    - - - - -
     *    - - - - -
     *
     * }
     * 2. To get lock of particular object b:
     *  -------------------------------
     * synchronized(b){
     *
     *    - - - - -
     *    - - - - -
     *
     * }
     * 3. To get class level lock
     * -------------------------------
     * synchronized(Display.class){
     *
     *    - - - - -
     *    - - - - -
     *
     * }
     * Lock concept applicable for object types anc class types, but not for primitives
     * hence we cant pass primitive type as argument to synchronized block. otherwise
     * we ll get compile time error saying Unexpected type, found : int ,require : reference
     * */
    public static void main(String[] args) {
        // If only one display object is there
        /*Display d = new Display();
        MyThread t1 = new MyThread(d,"dhoni");
        MyThread t2 = new MyThread(d,"yuvraj");
        t1.start();
        t2.start();*/

        // Suppose there are two display object
        // When thread are operating on different java objects, there is no need for synchronization
       /* Display d1 = new Display();
        Display d2 = new Display();
        MyThread t1 = new MyThread(d1,"dhoni");
        MyThread t2 = new MyThread(d2,"yuvraj");
        t1.start();
        t2.start();*/

       // Suppose there are two methods and we want to execute one by one
        /*Display d = new Display();
        MyThread1 t1 = new MyThread1(d);
        MyThread2 t2 = new MyThread2(d);
        t1.start();
        t2.start();*/

        //Synchronized block example
        //Display d = new Display();
        // let there be two display object d1 and d2, here we ll get irregular output, as lock is on
        //different object.
        Display d1 = new Display();
        Display d2 = new Display();
        MyThread t1 = new MyThread(d1,"dhoni");
        MyThread t2 = new MyThread(d2,"yuvraj");
        t1.start();
        t2.start();
    }

    static class Display {
        /* If a method is static synchronized and two thread are operating on two different objects
        * of display class, then the output will be regular output.*/
        public /*static synchronized */void  wish(String name) {
            // making block of code as synchronized
            ;;;;;;; // 1k lines of code
            //synchronized (this) { // current object lock
            synchronized (Display.class) { // class level lock
            //if we use int x =10; and
            // synchronized(x), we ll get unexpected exception
            // synchronized (this) { // current object lock
                for (int i = 0; i < 10; i++) {
                    System.out.print("Good Morning : ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name);
                }
            }
            ;;;;;;; // 1k lines of code
        }

        public synchronized void  displayn() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public  synchronized void  displayc() {
            for (int i = 65; i < 76; i++) {
                System.out.println((char) i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread extends Thread {
        Display d;
        String name;

        MyThread(Display dobj, String nameobj) {
            d = dobj;
            name = nameobj;
        }

        @Override
        public void run() {
            d.wish(name);
        }
    }

    static class MyThread1 extends Thread {
        Display d;

        MyThread1(Display dobj) {
            d = dobj;
        }

        @Override
        public void run() {
            d.displayn();
        }
    }

    static class MyThread2 extends Thread {
        Display d;

        MyThread2(Display dobj) {
            d = dobj;
        }

        @Override
        public void run() {
            d.displayc();
        }
    }
}
