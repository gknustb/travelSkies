package com.gknust.api;

import com.google.gson.*;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public class WeatherFetcher {
  private static final HttpClient client = HttpClient.newHttpClient();
  private static final Gson gson = new Gson();

  private final double[] latitude;
  private final double[] longitude;
  private final String[] day;


  public WeatherFetcher(double latitude, double longitude, String day){
    this.latitude = new double[] { latitude };
    this.longitude = new double[] { longitude };
    this.day = new String[] { day };
  }

  public WeatherResponse getWeather () throws WeatherException{

    WeatherQuery query = new WeatherQuery(latitude[0], longitude[0], day[0]);
    String jsonResponse = null;

    try {
      jsonResponse = fetch(gson.toJson(query));
    } catch (Exception e) {
      throw new WeatherException("Failed to fetch.", e);
    }

    //todo: check if response was valid
    if (jsonResponse != null) {
      return gson.fromJson(jsonResponse, WeatherResponse.class);
    }
    throw new WeatherException("Response was not valid.");
  }

  private String fetch (String jsonRequest) throws java.io.IOException, InterruptedException{
    HttpRequest.BodyPublisher requestQuery = HttpRequest.BodyPublishers.ofString(jsonRequest);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.open-meteo.com/v1/forecast"))
        .POST(requestQuery)
        .setHeader("Content-Type", "application/json")
        .timeout(Duration.ofSeconds(20))
        .build();

    //todo: handle bad responses
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }
}
