package com.gknust;
import com.gknust.web.Server;
import com.gknust.db.*;
import com.gknust.db.dao.*;
import com.gknust.dto.LocationCreateDTO;
import com.gknust.dto.TripCreateDTO;
import com.gknust.dto.UserCreateDTO;
import com.gknust.service.LocationService;
import com.gknust.service.TripService;
import com.gknust.service.UserService;
import com.gknust.web.adapter.TripAdapter;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {

        //todo: create DTOs for the data models
        //todo: create service layers to receive controller layer inputs and talk to DAOs
        //todo: create controller layer for each data object for proper encapsulation
        //todo: unit tests
        //todo: UI layer
        //todo: proper logging
        //todo(maybe): connection pool implementation

        try(Connection dbConnection = DatabaseConnection.getConnection()){
            DatabaseSchema schema = new DatabaseSchema(dbConnection);
            Server server = new Server();
            server.startServer();
            schema.dropDatabase();
            schema.initDatabase();

            //DAOs
            DaoFactory daoFactory = new DaoFactory(dbConnection);
            UserDAO userdao= daoFactory.initUserDAO();
            LocationDAO locationdao= daoFactory.initLocationDAO();
            TripDAO tripdao= daoFactory.initTripDAO();
            ClimateDAO climatedao= daoFactory.initClimateDAO();

            //Services
            UserService userService = new UserService(userdao);
            LocationService locationService = new LocationService(locationdao);
            TripService tripService = new TripService(tripdao, locationService, userService);

            //WebAdapters
            TripAdapter tripAdapter = new TripAdapter(tripService);

            //test operations
            UserCreateDTO newUser = new UserCreateDTO("test");
            userService.createUser(newUser);

            LocationCreateDTO newLocation = new LocationCreateDTO(-15.799661839592979, -47.864177046134174, "testloc");
            TripCreateDTO newTrip = new TripCreateDTO(userService.findUserByUsername("test").userID(), newLocation, 10230230, 91234180, "testtrip");
            tripService.createTrip(newTrip);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
