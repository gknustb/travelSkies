package com.gknust.dto;

public record LocationResponseDTO(
        int locationID,
        double latitude,
        double longitude,
        String displayName
) {
}
