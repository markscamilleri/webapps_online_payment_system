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
 */
public class UserJpaDao extends JpaDao<User> implements UserDao {

    
    @Override
    public Optional<User> getByUsername(String username) {
        return this.getByEqualsSingleParameter("username", username);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return this.getByEqualsSingleParameter("email", email);
    }
}
