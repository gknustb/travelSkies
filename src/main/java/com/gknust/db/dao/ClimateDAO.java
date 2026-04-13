package com.gknust.db.dao;

import com.gknust.model.Climate;
import com.gknust.model.Location;

import java.sql.Connection;
import java.util.List;

public class ClimateDAO {
    private final Connection connection;

    public ClimateDAO(Connection connection){
        this.connection = connection;
    }

//    public List<Climate> listClimatesByLocation(Location location){
//
//    }
}
