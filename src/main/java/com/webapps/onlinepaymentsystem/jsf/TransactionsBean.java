/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.ejb.CustomerUserService;
import com.webapps.onlinepaymentsystem.ejb.TransactionService;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author marks
 */
@Named("transactions")
@RequestScoped
public class TransactionsBean implements Serializable{

    private List<TransactionDto> transactionsReceived;
    private List<TransactionDto> transactionsSent;
    private CustomerUserDto userInfo;
    private String currencySymbol;
    private float balance;

    @EJB
    TransactionService transactionService;

    @EJB
    CustomerUserService customerUserService;

    @PostActivate
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String user = context.getExternalContext().getRemoteUser();

            userInfo = customerUserService.getUserByUsername(user);

            transactionsReceived = transactionService.getUserReceivedTransactions(userInfo.id);
            transactionsSent = transactionService.getUserSentTransactions(userInfo.id);
            currencySymbol = userInfo.currency.symbol;
            balance = userInfo.balance;
            
        } catch (UserNotFoundException ex) {
            Logger.getLogger(TransactionsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TransactionDto> getTransactionsReceived() {
        return transactionsReceived;
    }

    public void setTransactionsReceived(List<TransactionDto> transactionsReceived) {
        this.transactionsReceived = transactionsReceived;
    }

    public List<TransactionDto> getTransactionsSent() {
        return transactionsSent;
    }

    public void setTransactionsSent(List<TransactionDto> transactionsSent) {
        this.transactionsSent = transactionsSent;
    }

    public CustomerUserDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(CustomerUserDto userInfo) {
        this.userInfo = userInfo;
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
    
    
}
