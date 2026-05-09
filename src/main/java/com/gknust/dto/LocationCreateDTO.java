package com.gknust.dto;

public record LocationCreateDTO(
        double latitude,
        double longitude,
        String displayName
) {
}
