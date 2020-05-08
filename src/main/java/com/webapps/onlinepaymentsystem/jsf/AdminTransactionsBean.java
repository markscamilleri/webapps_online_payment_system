/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.jsf;

import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.ejb.TransactionService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author marks
 */
@Named("adminTransactions")
@RolesAllowed({"admin"})
@RequestScoped
public class AdminTransactionsBean implements Serializable {

    private List<TransactionDto> transactions;

    @EJB
    TransactionService transactionService;

    @PostActivate
    public void init() {
        transactions = transactionService.getAllTransactions();
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }
}
