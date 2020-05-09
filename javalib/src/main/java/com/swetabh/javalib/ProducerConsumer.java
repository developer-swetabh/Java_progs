package com.swetabh.javalib;

public class ProducerConsumer {

    /* Producer thread is responsible to produce items to the Queue, and consumer thread is
    *  responsible to consume items from the Queue.
    *  If Queue is empty then consumer thread will call wait() and entered into waiting state.
    *  After producing items to the queue, producer thread is responsible to call notify method
    *  then waiting consumer will get the notification and continue its execution with updated items
    * */
}
