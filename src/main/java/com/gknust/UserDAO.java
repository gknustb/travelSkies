package com.gknust;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public List<User> listUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User ORDER BY userID ASC";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet result = stmt.executeQuery())
        {
            while(result.next()){
                int userID = result.getInt("userID");
                String username = result.getString("username");
                users.add(new User(userID, username));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void insertUser(User user){
        String sql = "INSERT INTO User (username) VALUES (?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, user.getUsername());
            stmt.executeUpdate();
            try(ResultSet result= stmt.getGeneratedKeys()) {
                if (result.next()) {
                    int generatedKey = result.getInt(1);
                    user.setUserID(generatedKey);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
