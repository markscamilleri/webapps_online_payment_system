/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import java.util.Optional;

/**
 *
 * @author marks
 */
public interface CustomerUserDao extends Dao<CustomerUserDto> {
    Optional<CustomerUserDto> getByUsername(String username);

    Optional<CustomerUserDto> getByEmail(String email);

}
