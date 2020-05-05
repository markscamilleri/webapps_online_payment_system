/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.entity.PaymentNotification;
import com.webapps.onlinepaymentsystem.entity.User;
import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
import com.webapps.onlinepaymentsystem.enums.TimeCondition;
import java.util.Date;
import java.util.List;

/**
 *
 */
public interface PaymentNotificationDao extends Dao<PaymentNotification> {

    List<PaymentNotification> getByTimestamp(Date timestmap, TimeCondition when);
    
    List<PaymentNotification> getByStatus(PaymentNotificationStatus status);
        
}
