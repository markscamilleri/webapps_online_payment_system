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
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String user){
        super("User with the parameter " + user + " was not found");
    }
}
