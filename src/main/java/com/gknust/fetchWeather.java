package com.gknust;

import com.google.gson.*;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * fetchWeather
 */
public class fetchWeather {

  public static String fetch(String jsonRequest, HttpClient client) throws java.io.IOException, InterruptedException{
    HttpRequest.BodyPublisher requestQuery = HttpRequest.BodyPublishers.ofString(jsonRequest);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.open-meteo.com/v1/forecast"))
        .POST(requestQuery)
        .setHeader("Content-Type", "application/json")
        .build();

    //todo: handle bad responses
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response.body();
  }
}
