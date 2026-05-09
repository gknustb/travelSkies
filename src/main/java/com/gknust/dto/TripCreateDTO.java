package com.gknust.dto;

public record TripCreateDTO(
        int userID,
        LocationCreateDTO location,
        long startDate,
        long endDate,
        String name
) {
}
