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
public class RegistrationFailedException extends Exception{

    public RegistrationFailedException(String username, String email) {
        super("Registration for user with username " + username + " and email " + email + " failed.");
    }
}
