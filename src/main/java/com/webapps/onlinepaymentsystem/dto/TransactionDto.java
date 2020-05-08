/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import java.time.LocalDateTime;

/**
 *
 */
public class TransactionDto extends Dto{
    public LocalDateTime timestamp;
    public CustomerUserDto fromUser;
    public CustomerUserDto toUser;
    public String description;
    public float sendAmount;
    public CurrencyDto sendCurrency;
    public float recvAmount;
    public CurrencyDto recvCurrency;
}
