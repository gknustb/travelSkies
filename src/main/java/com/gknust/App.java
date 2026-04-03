package com.gknust;

import com.google.gson.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class App {
  public static void main(String[] args) {
    double[] latitude = { 52.12 };
    double[] longitude = { 20.54 };

    String[] day = { "2026-04-03" };

    postWeatherJSON weather = new postWeatherJSON(latitude, longitude, day);
    HttpClient client = HttpClient.newHttpClient();
    Gson jsonPOST = new Gson();

    System.out.println(jsonPOST.toJson(weather));
    System.out.println();
    try{
      String JSONresponse = fetchWeather.fetch(jsonPOST.toJson(weather), client);
      System.out.println(JSONresponse);
    }
    catch(Exception e){
      //todo: handle exception
    }
  }
}
