package com.gknust.model;

public class Climate {
    private long date;
    private Location location;
    private float maxTemp;
    private float minTemp;
    private int climateCode;
    private int precipitationChance;

    public Climate(long date, Location location, float maxTemp, float minTemp, int climateCode, int precipitationChance) {
        this.date = date;
        this.location = location;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.climateCode = climateCode;
        this.precipitationChance = precipitationChance;
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

    public int getPrecipitationChance() {
        return precipitationChance;
    }

    public void setPrecipitationChance(int precipitationChance) {
        this.precipitationChance = precipitationChance;
    }
}
