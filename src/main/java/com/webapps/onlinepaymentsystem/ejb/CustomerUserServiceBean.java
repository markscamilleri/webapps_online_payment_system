/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.CustomerUserDao;
import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
import com.webapps.onlinepaymentsystem.exceptions.RegistrationFailedException;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

/**
 *
 * @author marks
 */
@Stateless
@RolesAllowed({"admin", "user"})
public class CustomerUserServiceBean implements CustomerUserService {
    
    private static final float INITIAL_BALANCE = 1000f;

    @EJB
    CustomerUserDao customerUsers;

    @EJB
    UserService userService;

    @EJB
    TimeClientService timeService;

    @EJB
    CurrencyService currencyService;

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

    @Override
    @PermitAll
    @Transactional
    public void registerNewUser(String username, String email, String password, String chosenCurrencyShortName) throws RegistrationFailedException, CurrencyNotFoundException {
        userService.registerNewUser(username, email, password, "user");

        try {
            UserDto user = userService.getUserByUsername(username);
            CustomerUserDto custUser = new CustomerUserDto();

            custUser.user = user;
            custUser.currency = currencyService.getCurrencyByShortName(chosenCurrencyShortName);
            custUser.balance = INITIAL_BALANCE;
            
            customerUsers.create(custUser);
        } catch (UserNotFoundException ex) {
            Logger.getLogger(CustomerUserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegistrationFailedException(username, email);
        }
    }

    @Override
    @Transactional
    public void addToBalance(CustomerUserDto user, float toAdd) {
        user.balance += toAdd;
        customerUsers.update(user);
    }
}
