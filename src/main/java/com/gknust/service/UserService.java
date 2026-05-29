package com.gknust.service;

import com.gknust.db.dao.UserDAO;
import com.gknust.dto.UserCreateDTO;
import com.gknust.dto.UserResponseDTO;
import com.gknust.model.User;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserResponseDTO createUser(UserCreateDTO newUser){
        User createdUser = new User(newUser.username());
        userDAO.insertUser(createdUser);
        UserResponseDTO returnedUser = new UserResponseDTO(createdUser.getUsername(), createdUser.getUserID());
        return returnedUser;
    }

    public UserResponseDTO findUser(int userID){
        User foundUser = userDAO.findUserById(userID);
        UserResponseDTO returnedUser = new UserResponseDTO(foundUser.getUsername(), foundUser.getUserID());
        return returnedUser;
    }
    public UserResponseDTO findUserByUsername(String username){
        User foundUser = userDAO.findUserByUsername(username);
        UserResponseDTO returnedUser = new UserResponseDTO(foundUser.getUsername(), foundUser.getUserID());
        return returnedUser;
    }
}
