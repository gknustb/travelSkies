package com.gknust;
import com.gknust.db.*;
import com.gknust.db.dao.*;
import com.gknust.api.*;
import com.gknust.model.*;
import com.gknust.util.DateMath;

import java.sql.Connection;
import java.time.*;

public class App {
    public static void main(String[] args) {

        //todo: create DTOs for the data models
        //todo: create service layers to receive controller layer inputs and talk to DAOs
        //todo: create controller layer for each data object for proper encapsulation
        //todo: unit tests
        //todo: UI layer

        try(Connection dbConnection = DatabaseConnection.getConnection()){
            DatabaseSchema schema = new DatabaseSchema(dbConnection);

            schema.dropDatabase();
            schema.initDatabase();

            DaoFactory daoFactory = new DaoFactory(dbConnection);
            UserDAO userdao= daoFactory.initUserDAO();
            LocationDAO locationdao= daoFactory.initLocationDAO();
            TripDAO tripdao= daoFactory.initTripDAO();
            ClimateDAO climatedao= daoFactory.initClimateDAO();


            User test1 = new User("testuser");
            userdao.insertUser(test1);

            Location testlocation = new Location(-15.799612655608906, -47.86418960896907, 0L, "Brasilia");
            locationdao.insertLocation(testlocation);

            String dateString = "2026-04-17";
            long unixTime = DateMath.DateToUnix(dateString);
            System.out.println(DateMath.UnixToDate(unixTime));
            Trip testTrip = new Trip(test1, testlocation, unixTime, unixTime, "test trip");
            tripdao.insertTrip(testTrip);

            Climate testClimate = new Climate(unixTime, testlocation, 30.2F, 20.3F, 20, 12);
            climatedao.insertClimate(testClimate);

            for(User user : userdao.listUsers()){
                System.out.printf("\n%d | %s", user.getUserID(), user.getUsername());
            }

            testlocation.setDisplayName("DF");
            locationdao.updateLocation(testlocation);
            for(Location location : locationdao.listLocations()){
                System.out.printf("\n%g | %s", location.getLatitude(), location.getDisplayName());
            }

            for(Trip trip : tripdao.listTripsByUser(test1)){
                System.out.printf("\n%d | %s", trip.getTripID(), trip.getName());
            }

            for(Climate climate : climatedao.listClimatesByLocation(testlocation)){
                System.out.printf("\n%d | %f", climate.getDate(), climate.getMaxTemp());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
