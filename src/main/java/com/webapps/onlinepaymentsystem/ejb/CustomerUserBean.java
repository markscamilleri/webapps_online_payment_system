/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.CustomerUserDao;
import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marks
 */
@Stateless
@RolesAllowed({"admin", "user"})
public class CustomerUserBean implements CustomerUserService {

    @EJB
    CustomerUserDao customerUsers;

    @Override
    @RolesAllowed({"admin"})
    public List<CustomerUserDto> getAllUsers() {
        return customerUsers.getAll();
    }

    @Override
    public CustomerUserDto getUserById(long userId) throws UserNotFoundException {
        return customerUsers.getById(userId).orElseThrow(() -> new UserNotFoundException(Long.toString(userId)));
    }

    @Override
    public CustomerUserDto getUserByUsername(String username) throws UserNotFoundException {
        return customerUsers.getByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public CustomerUserDto getUserByEmail(String email) throws UserNotFoundException {
        return customerUsers.getByEmail(email).orElseThrow(() -> new UserNotFoundException(email));        
    }
    
}
