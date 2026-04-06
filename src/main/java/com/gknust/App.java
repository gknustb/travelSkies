package com.gknust;
import java.sql.*;

public class App {

    //private static final String dbUrl = "jdbc:sqlite:database.db";

    public static void main(String[] args) {
        var fetcher = new WeatherFetcher(-15.799612655608906, -47.86418960896907, "2026-04-08");
        try{
            WeatherResponse weather = fetcher.getWeather();
            System.out.println(weather.daily().temperature_2m_mean()[0]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

