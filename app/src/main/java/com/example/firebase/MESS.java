package com.example.firebase;

public class MESS {

    private String newMessage;
    private int timestamp;

    public MESS(String newMessage, int timestamp) {
        this.newMessage = newMessage;
        this.timestamp = timestamp;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
