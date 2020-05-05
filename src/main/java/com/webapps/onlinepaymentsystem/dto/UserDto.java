/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author marks
 */
public class UserDto {
    public Long id;
    public String username;
    public String email;
    public String encryptedPassword;
    public float balance;
    public CurrencyDto currency;
    public Date registrationTimestamp;
    public Date lastLogin;

    /**
     * This relation is lazy. Value will be present as a list (even empty) when loaded
     * null when these values have not been loaded by DAO.
     * 
     */
    public Optional<List<TransactionDTO>> transactionsOut;

    /**
     * This relation is lazy. Value will be present as a list (even empty) when
     * loaded null when these values have not been loaded by DAO.
     *
     */
    public Optional<List<TransactionDTO>> transactionsIn;

    /**
     * This relation is lazy. Value will be present as a list (even empty) when
     * loaded null when these values have not been loaded by DAO.
     *
     */
    public Optional<List<PaymentNotificationDTO>> notificationsSent;

    /**
     * This relation is lazy. Value will be present as a list (even empty) when
     * loaded null when these values have not been loaded by DAO.
     *
     */
    public Optional<List<PaymentNotificationDTO>> notificationsRecv;
}
