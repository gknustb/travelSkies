package com.gknust;

import com.google.gson.*;
import java.net.http.HttpClient;

public class App {
  private static final HttpClient client = HttpClient.newHttpClient();
  private static final Gson gson = new Gson();

  public static void main(String[] args) {

    double[] latitude = { 52.12 };
    double[] longitude = { 20.54 };
    String[] day = { "2026-04-03" };

    postWeatherJSON weather = new postWeatherJSON(latitude, longitude, day);
    String JSONResponse = null;

    try{
      JSONResponse = fetchWeather.fetch(gson.toJson(weather), client);
    }
    catch(Exception e){
      //todo: proper catching
      e.printStackTrace();
    }

    //todo: check if response was valid
    if(JSONResponse!=null){
      WeatherResponse responseObject = gson.fromJson(JSONResponse, WeatherResponse.class);
      System.out.println(JSONResponse);
      System.out.println(responseObject.daily().temperature_2m_mean()[0]);
    }
  }
}
