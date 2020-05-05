/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import java.util.Date;

/**
 *
 */
class TransactionDTO {
    public Long id;
    public Date timestamp;
    public UserDto fromUser;
    public UserDto toUser;
    public String description;
    public float sendAmount;
    public CurrencyDto sendCurrency;
    public float recvAmount;
    public CurrencyDto recvCurrency;
}
