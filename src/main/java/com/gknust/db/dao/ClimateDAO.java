package com.gknust.db.dao;

import com.gknust.model.Climate;
import com.gknust.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClimateDAO {
    private final Connection connection;

    public ClimateDAO(Connection connection){
        this.connection = connection;
    }

    public List<Climate> listClimatesByLocation(Location location){
        String sql = "SELECT * FROM Climate " +
                     "WHERE locationID = ? " +
                     "ORDER BY date DESC";

        List<Climate> climateList = new ArrayList<>();

        if(location == null || location.getLocationID() == 0)
            return climateList;

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, location.getLocationID());

            try(ResultSet selectedClimates = stmt.executeQuery()){
                while(selectedClimates.next()){
                    Climate returnedClimate = new Climate(
                            selectedClimates.getLong("date"),
                            location,
                            selectedClimates.getFloat("maxTemp"),
                            selectedClimates.getFloat("minTemp"),
                            selectedClimates.getInt("climateCode"),
                            selectedClimates.getInt("precipitationChance")
                    );

                    climateList.add(returnedClimate);
                }
            } catch (SQLException e){
                throw new RuntimeException("Failed to fetch results.", e);
            }
        }catch (SQLException e){
            throw new RuntimeException("Failed to send statement to database.", e);
        }
        return climateList;
    }

    public void insertClimate(Climate climate){
        String sql = "INSERT INTO Climate(date, locationID, maxTemp, minTemp, climateCode, precipitationChance) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, climate.getDate());
            stmt.setInt(2, climate.getLocation().getLocationID());
            stmt.setFloat(3, climate.getMaxTemp());
            stmt.setFloat(4, climate.getMinTemp());
            stmt.setInt(5, climate.getClimateCode());
            stmt.setInt(6, climate.getPrecipitationChance());

            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Failed to insert climate.", e);
        }
    }

    public void deleteClimate(Climate climate){
        String sql = "DELETE FROM Climate WHERE date = ? AND locationID = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, climate.getDate());
            stmt.setInt(2, climate.getLocation().getLocationID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to send delete statement.", e);
        }
    }
}
