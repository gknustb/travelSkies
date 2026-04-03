package com.gknust;

import com.google.gson.*;

public class App {
    public static void main(String[] args) {
        Weather weather = new Weather(50.3, 10.5, "2026-04-03");
        WeatherResponse response;

        try{
            response = weather.getWeather();
        }
        catch (WeatherException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.daily().temperature_2m_mean()[0]);
    }
}

