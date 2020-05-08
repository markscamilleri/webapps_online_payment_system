/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.ejb.UserGroupService;
import com.webapps.onlinepaymentsystem.ejb.UserService;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author marks
 */
@Named("loginCustomer")
@RequestScoped
public class LoginCustomerBean implements Serializable {

    private String username;
    private String password;

    private String errorMessage;

    @EJB
    private UserGroupService userGroupService;

    @EJB
    private UserService userService;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void redirectIfLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        String user = context.getExternalContext().getRemoteUser();

        if (user != null && !user.isEmpty()) {
            try {
                UserDto userObject = userService.getUserByUsername(username);
                if (userObject.userGroup.name == "admin") {
                    context.getExternalContext().redirect("/faces/admin/transactions");
                }
                if (userObject.userGroup.name == "user") {
                    context.getExternalContext().redirect("/faces/transactions");
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UserNotFoundException ex) {
                Logger.getLogger(RegisterCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String login() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println(username);
        
        try {
            request.login(username, password);

            String user = context.getExternalContext().getRemoteUser();

            if (!user.isEmpty()) {
                String role = userGroupService.getUserRole(user).name;
                return "admin".equals(role) ? "admin" : "customer";
            }

        } catch (ServletException | UserNotFoundException ex) {
            Logger.getLogger(LoginCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            setErrorMessage("There was a problem logging you in");
        }
        return null;
    }

    public String logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(LoginCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "logout";
    }
}
