package com.gknust.dto;

public record TripResponseDTO(
        int tripID,
        int userID,
        int locationID,
        long startDate,
        long endDate,
        String name
) {
}
