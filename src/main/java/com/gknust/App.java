package com.gknust;

public class App {
  public static void main( String[] args )
    {
        double[] latitude = {52.12};
        double[] longitude = {20.54};

        String[] day = {"2026-04-03"};

        postWeatherMethod weather = new postWeatherMethod(latitude, longitude, day);
        System.out.println(weather.latitude()[0]);
    }
}
