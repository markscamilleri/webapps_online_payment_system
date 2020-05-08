/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.ejb.CustomerUserService;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author marks
 */
@Named("payment")
@RolesAllowed({"user"})
@RequestScoped
public class PaymentBean implements Serializable {

    private String currencySymbol;
    private float balance;
    private String toUsername;
    private CustomerUserDto fromUser;
    private float amount;

    @EJB
    CustomerUserService customerUserService;

    @PostActivate
    public void init() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            
            String user = context.getExternalContext().getRemoteUser();
            
            fromUser = customerUserService.getUserByUsername(user);
            
            currencySymbol = fromUser.currency.symbol;
            balance = fromUser.balance;
        } catch (UserNotFoundException ex) {
            Logger.getLogger(PaymentBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendPayment() {

    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public CustomerUserDto getFromUser() {
        return fromUser;
    }

    public void setFromUser(CustomerUserDto fromUser) {
        this.fromUser = fromUser;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
