/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;

/**
 *
 * @author marks
 */
public interface UserService {

    public List<UserDto> getAllUsers();

    public List<UserDto> getAllAdmins();
    
    public UserDto getUserById(long userId) throws UserNotFoundException;

    public UserDto getUserByUsername(String username) throws UserNotFoundException;

    public UserDto getUserByEmail(String email) throws UserNotFoundException;
    
    public void registerNewUser(String username, String email, String password, String userGroup);
    
}
