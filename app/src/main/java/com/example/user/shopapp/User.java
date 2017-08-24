package com.example.user.shopapp;

/**
 * Created by USER on 8/24/2017.
 */
public class User {
    public String fname;
    public String lname;
    public String address;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fname1, String lname1, String address ,String email1) {
        this.fname = fname1;
        this.lname = lname1;
        this.address = address;
        this.email = email1;
    }
}
