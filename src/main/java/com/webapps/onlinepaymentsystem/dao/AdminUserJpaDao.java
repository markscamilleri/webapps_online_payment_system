/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.entity.AdminUser;
import java.util.Optional;

/**
 *
 * @author marks
 */
public class AdminUserJpaDao extends JpaDao<AdminUser> implements AdminUserDao {

    @Override
    public Optional<AdminUser> getByUsername(String username) {
        return this.getByEqualsSingleParameter("username", username);
    }

    @Override
    public Optional<AdminUser> getByEmail(String email) {
        return this.getByEqualsSingleParameter("email", email);
    }
    
}
