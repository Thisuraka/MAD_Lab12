package com.example.lab12_model;

public class Message {
    private int mid;
    private String user;
    private String subject;
    private String message;

    public Message() {
    }

    public Message(int mid, String user, String subject, String message) {
        this.mid = mid;
        this.user = user;
        this.subject = subject;
        this.message = message;
    }

    public Message(String user, String sub, String msg) {
        this.user = user;
        this.subject = sub;
        this.message = msg;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
