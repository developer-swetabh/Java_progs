package com.swetabh.javalib;

public class Deadlock {

    /* If two threads are waiting for each other forever, such type of infinite waiting is called deadlock.
     * Synchronized keyword is the only reason for deadlock situation, hence while using synchronized
     * keyword we have to take special care.
     * There are no resolution technique for deadlock but several prevention techniques are available.
     * In the below program if we remove at least one synchronized keyword then the program wont enter
     * into deadlock, hence synchronized keyword is the only reason for deadlock situation.
     * Due to this while usung synchronized keyword we have to take special care.
     *
     * Deadlock | vs | Starvation
     * -----------------------------------------------------------
     * Long waiting of a thread where waiting never ends, such type of situation is called deadlock.
     * Whereas long waiting of a thread where waiting ends at certain points is called starvation.
     * For e.g Low priority thread has to wait until completing all high priority threads, it may be
     * long waiting but ends at certain point, which is nothing but starvation
     * */

    public static void main(String[] args){
        System.out.println("Main thread calling m1 method of DeadlockThread");
        DeadlockThread th = new DeadlockThread();
        th.m1();

    }

    static class DeadlockThread extends Thread{
        A a = new A();
        B b = new B();

        public void m1(){
            this.start();
            a.d1(b); // This line is executed by main thread
        }

        @Override
        public void run() {
            b.d2(a); // This line is executed by child thread
        }
    }

    static class A {

        public synchronized void d1(B b){
            System.out.println("Thread1 starts execution of d1() method");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 trying to call B's last() method");
            b.last();
        }

        public synchronized void last(){
            System.out.println("Inside A's last() method");
        }
    }

    static class B{

        public synchronized void d2(A a){
            System.out.println("Thread2 starts execution of d2() method");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2 trying to call A's last() method");
            a.last();
        }

        public synchronized void last(){
            System.out.println("Inside B's last() method");
        }
    }

}
