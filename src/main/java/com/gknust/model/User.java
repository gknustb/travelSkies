package com.gknust.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID = 0;
    private String username;

    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public User(String username){
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
