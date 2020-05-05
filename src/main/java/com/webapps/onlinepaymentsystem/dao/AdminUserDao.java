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
 */
public interface AdminUserDao extends Dao<AdminUser> {

    Optional<AdminUser> getByUsername(String username);

    Optional<AdminUser> getByEmail(String email);

}
