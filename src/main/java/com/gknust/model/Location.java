package com.gknust.model;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private int locationID;
    private double latitude;
    private double longitude;
    private long lastUpdate;
    private String displayName;
    private List<Climate> climateList= new ArrayList<>();

    public Location(int locationID, double latitude, double longitude, long lastUpdate, String displayName) {
        this.locationID = locationID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastUpdate = lastUpdate;
        this.displayName = displayName;
    }

    public void addClimate(Climate climate){
        this.climateList.add(climate);
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
