/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import java.util.Optional;

/**
 *
 * @author marks
 */
public interface UserDao extends Dao<UserDto> {

    Optional<UserDto> getByUsername(String username);

    Optional<UserDto> getByEmail(String email);
}
