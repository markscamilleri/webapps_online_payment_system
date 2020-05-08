/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
import java.time.LocalDateTime;

/**
 *
 */
public class PaymentNotificationDto extends Dto {
    public LocalDateTime notificationTimestamp;
    public CustomerUserDto requestingUser;
    public CustomerUserDto payer;
    public String description;
    public float amount;
    public CurrencyDto currency;
    public PaymentNotificationStatus status;
}
