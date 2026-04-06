package com.gknust;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements AutoCloseable {
    private Connection connection;

    public void close() throws SQLException{
        try{
            this.connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDAO(){
        try{
            this.connection=ConnectionHandler.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> listUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
