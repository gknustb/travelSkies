package com.gknust.model;

public class Climate {
    private long date;
    private Location location;
    private float maxTemp;
    private float minTemp;
    private int climateCode;

    public Climate(long date, Location location, float maxTemp, float minTemp, int climateCode) {
        this.date = date;
        this.location = location;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.climateCode = climateCode;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public int getClimateCode() {
        return climateCode;
    }

    public void setClimateCode(int climateCode) {
        this.climateCode = climateCode;
    }
}
