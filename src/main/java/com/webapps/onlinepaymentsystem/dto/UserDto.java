/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import java.time.LocalDateTime;

/**
 *
 * @author marks
 */
public class UserDto extends Dto {
    public String username;
    public String email;
    public String encryptedPassword;
    public LocalDateTime registrationTimestamp;
    public LocalDateTime lastLogin;
    public UserGroupDto userGroup;
}
