package com.gknust.db.dao;

import com.gknust.model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    Connection connection;

    public LocationDAO(Connection connection){
        this.connection = connection;
    }

    public Location findLocationById(int locationID){
        String sql = "SELECT * FROM Location WHERE locationID = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, locationID);

            try(ResultSet selectedLocation = stmt.executeQuery()) {
                if (selectedLocation.next()) {
                    return new Location(
                            locationID,
                            selectedLocation.getDouble("latitude"),
                            selectedLocation.getDouble("longitude"),
                            selectedLocation.getLong("lastUpdate"),
                            selectedLocation.getString("displayName")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Location> listLocations(){
        String sql = "SELECT * FROM Location";
        List<Location> returnedLocations = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet selectedLocations = stmt.executeQuery()){

            while(selectedLocations.next()){
                Location resultLocation = new Location(
                        selectedLocations.getInt("locationID"),
                        selectedLocations.getDouble("latitude"),
                        selectedLocations.getDouble("longitude"),
                        selectedLocations.getLong("lastUpdate"),
                        selectedLocations.getString("displayName"));

                returnedLocations.add(resultLocation);
            }
        } catch (SQLException e){
            throw new RuntimeException("Failed to fetch database.", e);
        }

        return returnedLocations;
    }

    public void insertLocation(Location location){
        String sql = "INSERT INTO Location(LocationID, latitude, longitude, lastUpdate, displayName) " +
                "VALUES (NULL, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setDouble(1, location.getLatitude());
            stmt.setDouble(2, location.getLongitude());
            stmt.setLong(3, location.getLastUpdate());
            stmt.setString(4, location.getDisplayName());
            stmt.executeUpdate();

            try(ResultSet returnedKey = stmt.getGeneratedKeys()){
                if(returnedKey.next())
                    location.setLocationID(returnedKey.getInt(1));
            }

        } catch (SQLException e){
            throw new RuntimeException("Failed to insert.", e);
        }
    }

    public void updateLocation(Location location){
        String sql = "UPDATE Location " +
                "SET latitude = ?, longitude = ?, lastUpdate = ?, displayName = ? " +
                "WHERE locationID = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDouble(1, location.getLatitude());
            stmt.setDouble(2, location.getLongitude());
            stmt.setLong(3, location.getLastUpdate());
            stmt.setString(4, location.getDisplayName());
            stmt.setInt(5, location.getLocationID());

            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Failed to update.", e);
        }
    }

    public void deleteLocation(Location location){
        String sql = "DELETE FROM Location WHERE locationID = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, location.getLocationID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete.", e);
        }
    }

}
