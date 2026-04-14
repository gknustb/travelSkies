package com.gknust.api;

public record WeatherQuery(
    double[] latitude,
    double[] longitude,
    String[] daily,
    String[] timezone,
    String[] start_date,
    String[] end_date)
{
  public WeatherQuery(double latitude, double longitude, String day) {
      //this(latitude, longitude, new String[] { "temperature_2m_mean" }, new String[] { "auto" }, day, day);
      this(new double[]{latitude},
           new double[]{longitude},
           new String[]{"temperature_2m_mean"},
           new String[]{"auto"},
           new String[]{day},
           new String[]{day});
  }
}
