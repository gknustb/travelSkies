package com.gknust.web;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

public class Server {
    private static Tomcat server = new Tomcat();
    private int port;

    public Server(){
        this.port = 5342;
    }

    public void startServer(){
        try {
            server.setBaseDir("temp");
            server.setHostname("localhost");
            server.setPort(port);
            server.getConnector();
            String staticFiles = new java.io.File("src/main/resources/static").getAbsolutePath();
            Context context = server.addContext("", staticFiles);
            server.addServlet(context, "default", new DefaultServlet());
            context.addServletMappingDecoded("/", "default");

            server.addServlet(context, "travelApi", new TravelApiServlet());
            context.addServletMappingDecoded("/travel/save", "travelApi");
            server.start();
            System.out.println("Server started at http://localhost:"+port+"!");
            server.getServer().await();
        } catch (LifecycleException e) {
            System.out.println("Failed to start :(");
            throw new RuntimeException(e);
        }
    }

}
