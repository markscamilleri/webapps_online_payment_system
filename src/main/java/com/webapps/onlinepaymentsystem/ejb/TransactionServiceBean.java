/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.TransactionJpaDao;
import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
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

    @EJB(name="TransactionDao")
    private TransactionJpaDao transactionDao;

    @EJB
    private CustomerUserService customerUserService;

    @EJB
    private CurrencyService currencyService;

    @EJB
    private TimeClientService timeClient;

    @EJB
    private CurrencyConversionService currencyConversion;

    @Override
    @RolesAllowed({"admin"})
    public List<TransactionDto> getAllTransactions() {
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
    public void makePayment(
            CustomerUserDto fromUser,
            CustomerUserDto toUser,
            String description,
            CurrencyDto currency,
            float amount
    ) {

        TransactionDto newTx = new TransactionDto();

        newTx.fromUser = fromUser;
        newTx.toUser = toUser;
        newTx.sendCurrency = fromUser.currency;
        newTx.description = description;
        newTx.timestamp = timeClient.getTimeFromTimeServer();
        newTx.recvCurrency = toUser.currency;

        if (Objects.equals(currency.id, fromUser.currency.id)) {
            newTx.sendAmount = amount;
        } else {
            newTx.sendAmount = currencyConversion.convertCurrencyAmount(
                    fromUser.currency.shortName,
                    toUser.currency.shortName,
                    amount
            );
        }

        if (Objects.equals(currency.id, toUser.currency.id)) {
            newTx.recvAmount = amount;
        } else {
            newTx.recvAmount = currencyConversion.convertCurrencyAmount(
                    fromUser.currency.shortName,
                    toUser.currency.shortName,
                    amount
            );
        }

        transactionDao.create(newTx);
        customerUserService.addToBalance(fromUser, -1 * newTx.sendAmount);
        customerUserService.addToBalance(toUser, newTx.recvAmount);
    }

    @Override
    @Transactional
    public void makePayment(
            long fromUserId,
            String toUsernameOrEmail,
            String description,
            String currencyShortName,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException {
        CustomerUserDto fromUser = customerUserService.getUserById(fromUserId);

        CustomerUserDto toUser;

        try {
            toUser = customerUserService.getUserByEmail(toUsernameOrEmail);
        } catch (UserNotFoundException ex) {
            toUser = customerUserService.getUserByUsername(toUsernameOrEmail);
        }

        CurrencyDto currency = currencyService.getCurrencyByShortName(currencyShortName);

        makePayment(
                fromUser,
                toUser,
                description,
                currency,
                amount
        );
    }

    @Override
    @Transactional
    public void makePayment(
            long fromUserId,
            String toUsernameOrEmail,
            String description,
            long currencyId,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException {
        CustomerUserDto fromUser = customerUserService.getUserById(fromUserId);

        CustomerUserDto toUser = null;

        if (toUsernameOrEmail.contains("@")) {
            try {
                toUser = customerUserService.getUserByEmail(toUsernameOrEmail);
            } catch (UserNotFoundException ex) {
            }
        }

        if (toUser == null) {
            toUser = customerUserService.getUserByUsername(toUsernameOrEmail);
        }

        CurrencyDto currency = currencyService.getCurrencyById(currencyId);

        makePayment(
                fromUser,
                toUser,
                description,
                currency,
                amount
        );

    }

    @Override
    public void makePayment(
            long fromUserId,
            long toUserId,
            String description,
            long currencyId,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException {

        CustomerUserDto fromUser = customerUserService.getUserById(fromUserId);
        CustomerUserDto toUser = customerUserService.getUserById(toUserId);

        CurrencyDto currency = currencyService.getCurrencyById(currencyId);

        makePayment(fromUser, toUser, description, currency, amount);
    }

    @Override
    public void makePayment(
            long fromUserId, 
            long toUserId, 
            String description,
            String currencyShortName,
            float amount
    ) throws UserNotFoundException, CurrencyNotFoundException {
        CustomerUserDto fromUser = customerUserService.getUserById(fromUserId);
        CustomerUserDto toUser = customerUserService.getUserById(toUserId);

        CurrencyDto currency = currencyService.getCurrencyByShortName(currencyShortName);

        makePayment(fromUser, toUser, description, currency, amount);
    }

}
