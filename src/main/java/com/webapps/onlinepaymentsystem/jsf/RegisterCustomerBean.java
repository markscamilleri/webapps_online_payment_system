/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.ejb.CustomerUserService;
import com.webapps.onlinepaymentsystem.ejb.UserService;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
import com.webapps.onlinepaymentsystem.exceptions.RegistrationFailedException;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

/**
 *
 * @author marks
 */
@Named("registerCustomer")
@RequestScoped
public class RegisterCustomerBean implements Serializable {

    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+",
            message = "Email format is invalid.")
    private String email;

    private String username;
    private String password;
    private String password2;

    private String currency;

    private String errorMessage;

    @Inject
    CustomerUserService customerUserService;

    @Inject
    UserService userService;

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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String register() {
        try {
            customerUserService.registerNewUser(username, email, password, currency);
            return "registered";
        } catch (RegistrationFailedException ex) {
            Logger.getLogger(RegisterCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            setErrorMessage("There was an issue registering you");
        } catch (CurrencyNotFoundException ex) {
            Logger.getLogger(RegisterCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            setErrorMessage("There was a problem setting the currency");
        }
        return null;
    }

    public void redirectIfLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        String user = context.getExternalContext().getRemoteUser();

        if (user != null && !user.isEmpty()) {
            try {
                UserDto userObject = userService.getUserByUsername(username);
                if ("admin".equals(userObject.userGroup.name)) {
                    context.getExternalContext().redirect("/faces/admin/transactions");
                }
                if ("user".equals(userObject.userGroup.name)) {
                    context.getExternalContext().redirect("/faces/transactions");
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UserNotFoundException ex) {
                Logger.getLogger(RegisterCustomerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
