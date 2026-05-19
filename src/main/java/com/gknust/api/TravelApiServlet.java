package com.gknust.api;

import com.gknust.dto.TripCreateDTO;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TravelApiServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TripCreateDTO trip = gson.fromJson(request.getReader(), TripCreateDTO.class);
        request.getReader().lines().iterator().forEachRemaining(string -> System.out.println(string));
        //System.out.println(trip.name());

    }
}
