package com.gknust.db.dao;

import com.gknust.model.Location;
import com.gknust.model.Trip;
import com.gknust.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {
    private final Connection connection;

    public TripDAO(Connection connection){
        this.connection = connection;
    }

    public List<Trip> listTripsByUser(User user){
        String sql = "SELECT * FROM Trip t LEFT JOIN Location l " +
                     "ON t.locationID = l.locationID " +
                     "WHERE userID = ? " +
                     "ORDER BY tripID DESC";

        List<Trip> returnedTrips = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql)){

            boolean isUserInitialized = user!=null && user.getUserID() != 0;
            if(!isUserInitialized) return returnedTrips;

            stmt.setInt(1, user.getUserID());
            try(ResultSet selectedTrips = stmt.executeQuery()){

                while(selectedTrips.next()){
                    int tripID = selectedTrips.getInt("tripID");
                    long startDate = selectedTrips.getLong("startDate");
                    long endDate = selectedTrips.getLong("endDate");
                    String name = selectedTrips.getString("name");

                    int locationID = selectedTrips.getInt("locationID");
                    double latitude = selectedTrips.getDouble("latitude");
                    double longitude = selectedTrips.getDouble("longitude");
                    long lastUpdate = selectedTrips.getLong("lastUpdate");
                    String displayName = selectedTrips.getString("displayName");
                    Location tripLocation = new Location(locationID, latitude, longitude, lastUpdate, displayName);

                    returnedTrips.add(new Trip(tripID, user, tripLocation, startDate, endDate, name));
                }
            } catch (SQLException e){
                throw new RuntimeException("Could not get result from query.", e);
            }
        }catch (SQLException e){
            throw new RuntimeException("Could not prepare statement, connection failed.", e);
        }

        return returnedTrips;
    }

    public void insertTrip(Trip trip){
        String sql = "INSERT INTO Trip(tripID, userID, locationID, startDate, endDate, name) " +
                     "VALUES (NULL, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, trip.getUser().getUserID());
            stmt.setInt(2, trip.getLocation().getLocationID());
            stmt.setLong(3, trip.getStartDate());
            stmt.setLong(4, trip.getEndDate());
            stmt.setString(5, trip.getName());

            stmt.executeUpdate();

            try(ResultSet returnedKey = stmt.getGeneratedKeys()) {
                if (returnedKey.next())
                    trip.setTripID(returnedKey.getInt(1));
            }

        } catch (SQLException e){
            throw new RuntimeException("Failed to insert into database.", e);
        }
    }

    public void updateTrip(Trip trip){
        String sql = "UPDATE Trip " +
                "SET locationID = ?, startDate = ?, endDate = ?, name = ? " +
                "WHERE tripID = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, trip.getLocation().getLocationID());
            stmt.setLong(2, trip.getStartDate());
            stmt.setLong(3, trip.getEndDate());
            stmt.setString(4, trip.getName());
            stmt.setInt(5, trip.getTripID());

            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Failed to update.", e);
        }
    }

    public void deleteTrip(Trip trip){
        String sql = "DELETE FROM Trip WHERE tripID = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, trip.getTripID());
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
