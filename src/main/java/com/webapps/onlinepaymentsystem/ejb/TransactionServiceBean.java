/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.TransactionDao;
import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

/**
 *
 * @author marks
 */
@Stateless
@RolesAllowed({"user"})
public class TransactionServiceBean implements TransactionService {

    @EJB(name = "transactionDao")
    private TransactionDao transactionDao;

    @EJB
    private CustomerUserService customerUserService;

    @EJB
    private TimeClientService timeClient;

    @EJB
    private CurrencyConversionService currencyConversion;
    
    @Override
    @RolesAllowed({"admin"})
    public List<TransactionDto> getAllTransactions(){
        return transactionDao.getAll();
    }

    @Override
    public List<TransactionDto> getUserSentTransactions(long userId) {
        return transactionDao.getBySenderId(userId).orElse(new ArrayList<>());
    }

    @Override
    public List<TransactionDto> getUserReceivedTransactions(long userId) {
        return transactionDao.getByReceiverId(userId).orElse(new ArrayList<>());
    }

    @Override
    @Transactional
    public void makePayment(
            long fromUserId,
            String toUsernameOrEmail,
            String description,
            float amount
    ) throws UserNotFoundException {
        TransactionDto newTx = new TransactionDto();

        CustomerUserDto fromUser = customerUserService.getUserById(fromUserId);
        CustomerUserDto toUser = null;

        if (toUsernameOrEmail.contains("@")) {
            try {
                toUser = customerUserService.getUserByEmail(toUsernameOrEmail);
            } catch (UserNotFoundException ex) {
                toUser = null;
            }
        }

        if (toUser == null) {
            toUser = customerUserService.getUserByUsername(toUsernameOrEmail);
        }

        newTx.fromUser = fromUser;
        newTx.toUser = toUser;
        newTx.sendAmount = amount;
        newTx.sendCurrency = fromUser.currency;
        newTx.description = description;
        newTx.timestamp = timeClient.getTimeFromTimeServer();
        newTx.recvCurrency = toUser.currency;

        if (Objects.equals(fromUser.currency.id, toUser.currency.id)) {
            newTx.sendAmount = amount;
        } else {
            newTx.sendAmount = currencyConversion.convertCurrencyAmount(
                    fromUser.currency.shortName,
                    toUser.currency.shortName,
                    amount
            );
        }

        transactionDao.create(newTx);
    }
}
