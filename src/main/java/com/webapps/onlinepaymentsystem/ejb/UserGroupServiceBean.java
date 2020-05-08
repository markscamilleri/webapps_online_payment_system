/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.UserJpaDao;
import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.dto.UserGroupDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed({"user", "admin"})
public class UserGroupServiceBean implements UserGroupService {

    @EJB(name="UserDao")
    UserJpaDao userDao;

    @Override
    public UserGroupDto getUserRole(String username) throws UserNotFoundException{
        UserDto user = userDao.getByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        
        return user.userGroup;
    }

}
