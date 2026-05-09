package com.gknust.service;

import com.gknust.db.dao.TripDAO;
import com.gknust.dto.LocationResponseDTO;
import com.gknust.dto.TripCreateDTO;
import com.gknust.dto.TripResponseDTO;
import com.gknust.dto.UserResponseDTO;
import com.gknust.model.Location;
import com.gknust.model.Trip;
import com.gknust.model.User;

public class TripService {
    private final TripDAO tripDAO;
    private final LocationService locationService;
    private final UserService userService;

    public TripService(TripDAO tripDAO, LocationService locationService, UserService userService){
        this.tripDAO = tripDAO;
        this.locationService = locationService;
        this.userService = userService;
    }

    public TripResponseDTO createTrip(TripCreateDTO newTrip){
        LocationResponseDTO newlocation = locationService.createLocation(newTrip.location());
        UserResponseDTO foundUser = userService.findUser(newTrip.userID());
        User tripUser = new User(foundUser.userID(), foundUser.username());
        Location tripLocation = new Location(newlocation.locationID(), newlocation.latitude(), newlocation.longitude(), newlocation.displayName());
        Trip createdTrip = new Trip(tripUser, tripLocation, newTrip.startDate(), newTrip.endDate(), newTrip.name());
        tripDAO.insertTrip(createdTrip);
        TripResponseDTO returnedTrip = new TripResponseDTO(createdTrip.getTripID(), tripUser.getUserID(), tripLocation.getLocationID(), createdTrip.getStartDate(), createdTrip.getEndDate(), createdTrip.getName());
        return returnedTrip;
    }
}
