package com.swetabh.javalib.producerconsumer;

/* Simple POJO class which contains the message.
 * DataQueue will have list of messages in queue data structure.
 * Producer will create a message and add it to the DataQueue.
 * Consumer will consume the message from DataQueue.
 * */
public class Message {
    private int id;
    private double data;

    public Message(int id, double data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
