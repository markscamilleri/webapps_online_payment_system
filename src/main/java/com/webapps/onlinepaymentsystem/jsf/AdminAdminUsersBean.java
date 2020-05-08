/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.ejb.UserService;
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
@Named("adminsers")
@RolesAllowed({"admin"})
@ViewScoped
public class AdminAdminUsersBean {

    private List<UserDto> users;

    @Inject
    UserService userService;

    @PostActivate
    public void init() {
        users = userService.getAllAdmins();
    }
    
    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
