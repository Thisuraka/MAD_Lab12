package com.example.lab12_model;

public class User {
    private int uid;
    private String name;
    private String password;
    private String type;

    public User() {
    }

    public User(int uid, String name, String password, String type) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public User(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
