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
                     "WHERE locationID = ?";

        List<Climate> climateList = new ArrayList<>();

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
}
