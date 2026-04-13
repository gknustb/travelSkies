package com.gknust.db.dao;

import com.gknust.model.Climate;
import com.gknust.model.Trip;
import com.gknust.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {
    private final Connection connection;

    public TripDAO(Connection connection){
        this.connection = connection;
    }

    //todo: fix this, current problem: how do I get the location object if I only have the ID?
    //todo: cant initialize the LocationDAO inside this class for this, becomes spaghetti
    public List<Trip> listTripsByUser(User user){
        String sql = "SELECT * FROM Trip WHERE userID = ? ORDER BY tripID DESC";
        List<Trip> returnedTrips = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            //if user does not have an ID initialized yet
            if(user.getUserID() == 0) return returnedTrips;

            stmt.setInt(1, user.getUserID());
            try(ResultSet selectedTrips = stmt.executeQuery()){

                while(selectedTrips.next()){
                    int tripID = selectedTrips.getInt(1);
                    int locationID = selectedTrips.getInt("locationID");
                    long startDate = selectedTrips.getLong("startDate");
                    long endDate = selectedTrips.getLong("endDate");
                    String name = selectedTrips.getString("name");

                    returnedTrips.add(new Trip(tripID, user, locationID, startDate, endDate, name));
                }
            } catch (SQLException e){
                throw new RuntimeException("Could not get result from query.", e);
            }
        }catch (SQLException e){
            throw new RuntimeException("Could not prepare statement, connection failed.", e);
        }

        return returnedTrips;
    }

//    public void insertTrip(Trip trip){
//        String sql = "INSERT INTO Trip(tripID, userID, locationID, startDate, endDate, name) " +
//                "VALUES(?, ?, ?, ?, ?, ?)";
//
//        try(PreparedStatement stmt = connection.prepareStatement(sql)){
//            stmt.setInt(trip.getTripID());
//            stmt.setInt(trip.getUser().getUserID());
//            stmt.setInt(trip.);
//        }
//    }

    //todo: CRUD: create, update, delete, more list methods
}
