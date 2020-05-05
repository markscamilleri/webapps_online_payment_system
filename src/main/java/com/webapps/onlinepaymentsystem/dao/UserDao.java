/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.entity.User;
import java.util.Optional;

/**
 *
 * @author marks
 */
public interface UserDao extends Dao<User> {

    Optional<User> getByUsername(String username);

    Optional<User> getByEmail(String email);
}
