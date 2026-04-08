package com.gknust;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String dbUrl = "jdbc:sqlite:database.db";

    public static Connection getConnection() throws SQLException{
       return DriverManager.getConnection(dbUrl);
    }

    public static void initDatabase(){
        try(Connection connection = )
    }
}
