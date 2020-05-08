/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.exceptions;

/**
 *
 * @author marks
 */
public class LoginFailedException extends Exception{

    public LoginFailedException(String username) {
        super("Login failed for user " + username);
    }
}
