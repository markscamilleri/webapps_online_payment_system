/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.PaymentNotificationDto;
import com.webapps.onlinepaymentsystem.exceptions.PaymentNotificationNotFound;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;

/**
 *
 * @author marks
 */
public interface PaymentNotificationService {

    public List<PaymentNotificationDto> getUnreadPaymentNotifications(long userId) throws UserNotFoundException;

    public void acceptNotificationAndMakeTransaction(PaymentNotificationDto notification);

    public void acceptNotificationAndMakeTransaction(long notificationId) throws PaymentNotificationNotFound;

    public void rejectNotification(PaymentNotificationDto notification);

    public void rejectNotification(long notificationId) throws PaymentNotificationNotFound;

    /**
     * Currency is inferred from fromUser
     */
    public void sendNotification(CustomerUserDto fromUser, CustomerUserDto toUser, String description, float amount);

    public void sendNotification(long fromUserId, long toUserId, String description, float amount) throws UserNotFoundException;

    public void sendNotification(long fromUserId, String toUsernameOrEmail, String description, float amount) throws UserNotFoundException;
}
