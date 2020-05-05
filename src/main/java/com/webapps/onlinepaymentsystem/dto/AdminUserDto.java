/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import java.util.Date;

/**
 *
 * @author marks
 */
public class AdminUserDto {
    public Long id;
    public String username;
    public String email;
    public String encryptedPassword;
    public Date registrationTimestamp;
    public Date lastLogin;

}
