package com.gknust.dto;

public record TripDTO(
        int userID,
        int locationID,
        long startDate,
        long endDate,
        String name
) {
}
