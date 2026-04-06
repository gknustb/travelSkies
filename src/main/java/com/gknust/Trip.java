package com.gknust;

public class Trip {

    private int tripID;
    private User user;
    private int locationID;
    private long startDate;
    private long endDate;
    private String name;


    public Trip(int tripID, User user, int locationID, long startDate, long endDate, String name) throws IllegalArgumentException{
        if(startDate<endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }else
            throw new IllegalArgumentException("Start date cannot be later than end date.");

        this.tripID = tripID;
        this.user = user;
        this.locationID = locationID;
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
