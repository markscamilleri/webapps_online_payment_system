/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.ejb.CustomerUserService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.PostActivate;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author marks
 */
@Named("adminCustomerUsers")
@RolesAllowed({"admin"})
@ViewScoped
public class AdminCustomerUsersBean implements Serializable {

    private List<CustomerUserDto> users;

    @Inject
    CustomerUserService customerUserService;
    
    @PostActivate
    public void init() {
        users = customerUserService.getAllUsers();
    }

    public List<CustomerUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<CustomerUserDto> users) {
        this.users = users;
    }
    
    
}
