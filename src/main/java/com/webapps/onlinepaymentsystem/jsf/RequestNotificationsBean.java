/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.PaymentNotificationDto;
import com.webapps.onlinepaymentsystem.ejb.CustomerUserService;
import com.webapps.onlinepaymentsystem.ejb.PaymentNotificationService;
import com.webapps.onlinepaymentsystem.ejb.TimeClientBean;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.PostActivate;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

/**
 *
 * @author marks
 */
@Named("requestNotifications")
@RolesAllowed({"user"})
@ViewScoped
public class RequestNotificationsBean implements Serializable {
    
    private List<PaymentNotificationDto> requestNotifications;
    private CustomerUserDto userInfo;
    
    private String toUsername;
    
    private String currency;
    
    private String description;
    
    private String errorMessage;
    
    @Pattern(regexp = "[0-9]+\\.[0-9]{2}", message = "Amount is invalid")
    private String amount;
    
    @Inject
    PaymentNotificationService paymentNotificationServiceBean;
    
    @Inject
    CustomerUserService customerUserService;
    
    @Inject
    transient TimeClientBean timeClientBean;
    
    @PostActivate
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String user = context.getExternalContext().getRemoteUser();
            
            userInfo = customerUserService.getUserByUsername(user);
            requestNotifications = paymentNotificationServiceBean.getUnreadPaymentNotifications(userInfo.id);
            currency = userInfo.currency.symbol;
            
        } catch (UserNotFoundException ex) {
            Logger.getLogger(TransactionsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void acceptRequest(PaymentNotificationDto request) {
        paymentNotificationServiceBean.acceptNotificationAndMakeTransaction(request);
    }
    
    public void rejectRequest(PaymentNotificationDto request) {
        paymentNotificationServiceBean.rejectNotification(request);
    }
    
    public void makeRequest() {
        try {
            paymentNotificationServiceBean.sendNotification(
                    userInfo.id,
                    toUsername,
                    description,
                    Float.parseFloat(amount)
            );
        } catch (UserNotFoundException ex) {
            Logger.getLogger(RequestNotificationsBean.class.getName()).log(Level.SEVERE, null, ex);
            setErrorMessage(ex.getMessage());
        }
    }
    
    public List<PaymentNotificationDto> getRequestNotifications() {
        return requestNotifications;
    }
    
    public void setRequestNotifications(List<PaymentNotificationDto> requestNotifications) {
        this.requestNotifications = requestNotifications;
    }
    
    public CustomerUserDto getUserInfo() {
        return userInfo;
    }
    
    public void setUserInfo(CustomerUserDto userInfo) {
        this.userInfo = userInfo;
    }
    
    public String getToUsername() {
        return toUsername;
    }
    
    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
