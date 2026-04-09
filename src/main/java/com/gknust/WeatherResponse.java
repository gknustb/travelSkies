package com.gknust;

public record WeatherResponse(
    double latitude,
    double longitude,

    double generationtime_ms,
    int utc_offset_seconds,

    String timezone,
    String elevation,

    Daily daily

) {
}

record Daily(
        String[] time,
        double[] temperature_2m_mean
){}