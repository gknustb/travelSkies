package com.gknust;

public class Trip {

    //todo:  after defining business rules, validate date logic when setting
    private int tripID;
    private User user;
    private int locationID;
    private long startDate;
    private long endDate;
    private String name;


    public Trip(int tripID, User user, int locationID, long startDate, long endDate, String name) {
        this.tripID = tripID;
        this.user = user;
        this.locationID = locationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
