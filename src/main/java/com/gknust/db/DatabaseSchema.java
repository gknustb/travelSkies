package com.gknust.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSchema {
    private final Connection connection;
    private final String sqlSchema = "db/schema.sql";
    private final String sqlDrop = "";

    public DatabaseSchema(Connection connection){
        this.connection = connection;
    }


    public void initDatabase(){
        try(InputStream sqlInputStream = getClass().getClassLoader().getResourceAsStream(sqlSchema)){
            if(sqlInputStream == null){
                throw new RuntimeException("File could not be read: " + sqlSchema);
            }

            //reading whole file at once works in sqlite, though it looks like it may not work on other SQL databases
            byte[] sqlStringBytes = sqlInputStream.readAllBytes();
            String sql = new String(sqlStringBytes, StandardCharsets.UTF_8);

            try (Statement stmt = connection.createStatement()){
                stmt.executeUpdate(sql);
            }catch (java.sql.SQLException e) {
               throw new RuntimeException(e);
            }

        }catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropDatabase(){
        String sqlSelect = "SELECT name FROM sqlite_schema WHERE type = 'table' AND name NOT LIKE 'sqlite_%'";

        try(Statement stmt = connection.createStatement();
            ResultSet selectedNames = stmt.executeQuery(sqlSelect)){
            List<String> tableNames = new ArrayList<>();

            while(selectedNames.next()){
                tableNames.add(selectedNames.getString("name"));
            }

            stmt.executeUpdate("PRAGMA foreign_keys = OFF");

            for (String name : tableNames){
                String sqlDrop = ("DROP TABLE " + name);
                stmt.executeUpdate(sqlDrop);
            }

            stmt.executeUpdate("PRAGMA foreign_keys = ON");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
