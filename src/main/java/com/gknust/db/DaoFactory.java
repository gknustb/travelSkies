package com.gknust.db;

import com.gknust.db.dao.ClimateDAO;
import com.gknust.db.dao.LocationDAO;
import com.gknust.db.dao.TripDAO;
import com.gknust.db.dao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private final Connection connection;
    private final UserDAO userDAO;
    private final LocationDAO locationDAO;
    private final TripDAO tripDAO;
    private final ClimateDAO climateDAO;

    public DaoFactory(Connection connection) throws SQLException {
        this.connection = connection;
        this.userDAO = new UserDAO(this.connection);
        this.locationDAO = new LocationDAO(this.connection);
        this.tripDAO = new TripDAO(this.connection);
        this.climateDAO = new ClimateDAO(this.connection);
    }

    public UserDAO initUserDAO(){
        return userDAO;
    }

    public LocationDAO initLocationDAO(){
        return locationDAO;
    }

    public TripDAO initTripDAO(){
        return tripDAO;
    }

    public ClimateDAO initClimateDAO(){
        return climateDAO;
    }
}
