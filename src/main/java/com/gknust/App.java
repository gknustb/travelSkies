package com.gknust;
import com.gknust.*;
import com.gknust.db.*;
import com.gknust.db.dao.*;
import com.gknust.api.*;
import com.gknust.model.*;

import java.sql.Connection;

public class App {

    //private static final String dbUrl = "jdbc:sqlite:database.db";

    public static void main(String[] args) {
//        var fetcher = new WeatherFetcher(-15.799612655608906, -47.86418960896907, "2026-04-08");
//        try{
//            WeatherResponse weather = fetcher.getWeather();
//            System.out.println(weather.daily().temperature_2m_mean()[0]);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        try(Connection dbConnection = DatabaseConnection.getConnection()){
            DatabaseSchema schema = new DatabaseSchema(dbConnection);

            schema.dropDatabase();
            schema.initDatabase();

            User test1 = new User("testuser");
            Location testlocation = new Location(-15.799612655608906, -47.86418960896907, 0L, "Brasilia");

            UserDAO userdao = new UserDAO(dbConnection);
            LocationDAO locationdao = new LocationDAO(dbConnection);

            userdao.insertUser(test1);
            locationdao.insertLocation(testlocation);

            for(User user : userdao.listUsers()){
                System.out.printf("\n%d | %s", user.getUserID(), user.getUsername());
            }
            testlocation.setDisplayName("DF");
            locationdao.updateLocation(testlocation);
            for(Location location : locationdao.listLocations()){
                System.out.printf("\n%g | %s", location.getLatitude(), location.getDisplayName());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
