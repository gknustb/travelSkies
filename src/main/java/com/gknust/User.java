package com.gknust;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID;
    private String username;
    private List<Trip> trips = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {   //todo: add method to add trip to list and to return the list (or a copy of it)
        this.userID = userID;
    }

    //todo: add method to add trip to list and to return the list (or a copy of it)
}
