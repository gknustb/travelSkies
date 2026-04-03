package com.gknust;

import com.google.gson.JsonObject;

public record WeatherResponse(
    double latitude,
    double longitude,

    double generationtime_ms,
    int utc_offset_seconds,

    String timezone,
    String elevation,

    daily daily

) {
}

record daily(
        String[] time,
        double[] temperature_2m_mean
){}