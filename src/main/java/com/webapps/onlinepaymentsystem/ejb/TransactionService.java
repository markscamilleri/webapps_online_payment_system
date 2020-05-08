/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
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
            CustomerUserDto fromUser,
            CustomerUserDto toUser,
            String description,
            CurrencyDto currency,
            float amount
    );

    public void makePayment(
            long fromUserId,
            String toUsernameOrEmail,
            String description,
            String currencyShortName,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException;

    public void makePayment(
            long fromUserId,
            String toUsernameOrEmail,
            String description,
            long currencyId,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException;

    public void makePayment(
            long fromUserId,
            long toUserId,
            String description,
            String currencyShortName,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException;

    public void makePayment(
            long fromUserId,
            long toUserId,
            String description,
            long currencyId,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException;
}
