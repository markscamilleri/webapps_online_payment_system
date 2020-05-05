/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dto;

import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
import java.util.Date;

/**
 *
 * @author marks
 */
class PaymentNotificationDTO {
    public Long id;
    public Date notificationTimestamp;
    public UserDto requestingUser;
    public UserDto payer;
    public String description;
    public float amount;
    public CurrencyDto currency;
    public PaymentNotificationStatus status;
}
