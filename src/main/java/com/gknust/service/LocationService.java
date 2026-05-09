package com.gknust.service;

import com.gknust.db.dao.LocationDAO;
import com.gknust.dto.LocationCreateDTO;
import com.gknust.dto.LocationResponseDTO;
import com.gknust.model.Location;

public class LocationService {
    private final LocationDAO locationDAO;
    public LocationService(LocationDAO locationDAO){
        this.locationDAO = locationDAO;
    }

    public LocationResponseDTO createLocation(LocationCreateDTO newLocation){
        Location createdLocation = new Location(
                newLocation.latitude(),
                newLocation.longitude(),
                newLocation.displayName()
        );

        locationDAO.insertLocation(createdLocation);
        LocationResponseDTO returnedLocation = new LocationResponseDTO(
                createdLocation.getLocationID(),
                createdLocation.getLatitude(),
                createdLocation.getLongitude(),
                createdLocation.getDisplayName()
        );

        return returnedLocation;
    }
}
