/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;

/**
 *
 * @author marks
 */
public interface TransactionService {
    public List<TransactionDto> getAllTransactions();
    public List<TransactionDto> getUserSentTransactions(long userId);
    public List<TransactionDto> getUserReceivedTransactions(long userId);
    public void makePayment(
            long fromUserId, 
            String toUsernameOrEmail, 
            String shortCurrencyName,
            float amount
    ) throws UserNotFoundException;
}
