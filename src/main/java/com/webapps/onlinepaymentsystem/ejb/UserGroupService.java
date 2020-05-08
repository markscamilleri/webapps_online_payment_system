/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.UserGroupDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;

/**
 *
 * @author marks
 */
public interface UserGroupService {
    public UserGroupDto getUserRole(String username) throws UserNotFoundException;    
}
