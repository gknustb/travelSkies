package com.gknust.model;

public class Trip {

    private int tripID;
    private User user;
    private Location location;
    private long startDate;
    private long endDate;
    private String name;


    public Trip(int tripID, User user, Location location, long startDate, long endDate, String name) throws IllegalArgumentException{
        if(startDate<=endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }else
            throw new IllegalArgumentException("Start date cannot be later than end date.");

        this.tripID = tripID;
        this.user = user;
        this.location = location;
        this.name = name;
    }

    public Trip(User user, Location location, long startDate, long endDate, String name) throws IllegalArgumentException{
        if(startDate<=endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }else
            throw new IllegalArgumentException("Start date cannot be later than end date.");

        this.user = user;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) throws IllegalArgumentException{
        if(startDate<endDate)
            this.startDate = startDate;
        else
            throw new IllegalArgumentException("Start date cannot be later than end date.");
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) throws IllegalArgumentException{
        if(startDate<endDate)
            this.endDate = endDate;
        else
            throw new IllegalArgumentException("End date cannot be earlier than start date.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
