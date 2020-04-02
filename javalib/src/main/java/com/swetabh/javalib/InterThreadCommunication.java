package com.swetabh.javalib;

public class InterThreadCommunication {

    /* Two threads can communicate with each other by using wait(), notify() and notifyAll() methods
    *  the thread which is expecting updation is responsible to call wait method, then immediately
    * the thread will enter into waiting state.
    * The thread which is responsible to perform updation , after performing updation it is responsible
    * to call notify() then waiting thread will get the notification and continue its execution with
    * those updated items.
    * wait(), notify() and notifyAll() methods are present in Object class not in Thread class.*/
    public static void main(String[] args){

    }
}
