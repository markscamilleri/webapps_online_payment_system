
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
import com.webapps.onlinepaymentsystem.exceptions.RegistrationFailedException;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;

/**
 *
 * @author marks
 */
public interface CustomerUserService {

    public List<CustomerUserDto> getAllUsers();

    public CustomerUserDto getUserById(long userId) throws UserNotFoundException;

    public CustomerUserDto getUserByUsername(String username) throws UserNotFoundException;

    public CustomerUserDto getUserByEmail(String email) throws UserNotFoundException;

    public void registerNewUser(String username, String email, String password, String chosenCurrencyShortName) throws RegistrationFailedException, CurrencyNotFoundException;
    
    public void addToBalance(CustomerUserDto user, float toAdd);
}
