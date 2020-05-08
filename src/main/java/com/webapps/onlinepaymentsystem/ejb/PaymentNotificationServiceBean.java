/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.PaymentNotificationJpaDao;
import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.dto.PaymentNotificationDto;
import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
import com.webapps.onlinepaymentsystem.exceptions.PaymentNotificationNotFound;
import com.webapps.onlinepaymentsystem.exceptions.UserNotFoundException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed({"user"})
public class PaymentNotificationServiceBean implements PaymentNotificationService {

    @EJB
    PaymentNotificationJpaDao paymentNotificationDao;

    @EJB
    TransactionService transactionService;
    
    @EJB
    CustomerUserService customerUserService;
        
    @EJB
    TimeClientService timeClientService;

    @Override
    public List<PaymentNotificationDto> getUnreadPaymentNotifications(long userId) throws UserNotFoundException {
        return paymentNotificationDao.getUnreadByPayerId(userId).orElseThrow(() -> new UserNotFoundException(Long.toString(userId)));
    }

    @Override
    public void acceptNotificationAndMakeTransaction(PaymentNotificationDto notification) {
        transactionService.makePayment(
                notification.payer,
                notification.requestingUser,
                notification.description,
                notification.currency,
                notification.amount
        );

        notification.status = PaymentNotificationStatus.ACCEPTED;

        paymentNotificationDao.update(notification);
    }

    @Override
    public void acceptNotificationAndMakeTransaction(long notificationId) throws PaymentNotificationNotFound{
        PaymentNotificationDto notification = paymentNotificationDao
                .getById(notificationId)
                .orElseThrow(() -> new PaymentNotificationNotFound(Long.toString(notificationId)));
        
        acceptNotificationAndMakeTransaction(notification);
    }

    @Override
    public void rejectNotification(PaymentNotificationDto notification) {
        notification.status = PaymentNotificationStatus.REJECTED;
        
        paymentNotificationDao.update(notification);
    }

    @Override
    public void rejectNotification(long notificationId) throws PaymentNotificationNotFound{
        PaymentNotificationDto notification = paymentNotificationDao
                .getById(notificationId)
                .orElseThrow(() -> new PaymentNotificationNotFound(Long.toString(notificationId)));

        rejectNotification(notification);
    }

    @Override
    public void sendNotification(CustomerUserDto fromUser, CustomerUserDto toUser, String description, float amount) {
        PaymentNotificationDto notification = new PaymentNotificationDto();
        
        notification.requestingUser = fromUser;
        notification.payer = toUser;
        notification.currency = fromUser.currency;
        notification.amount = amount;
        notification.notificationTimestamp = timeClientService.getTimeFromTimeServer();
        notification.status = PaymentNotificationStatus.UNSET;
        
        paymentNotificationDao.create(notification);
    }

    @Override
    public void sendNotification(long fromUserId, long toUserId, String description, float amount) throws UserNotFoundException {
        CustomerUserDto fromUser = customerUserService.getUserById(fromUserId);
        CustomerUserDto toUser = customerUserService.getUserById(toUserId);
        
        sendNotification(fromUser, toUser, description, amount);
    }

    @Override
    public void sendNotification(long fromUserId, String toUsernameOrEmail, String description, float amount) throws UserNotFoundException {
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

        sendNotification(fromUser, toUser, description, amount);
    }
}
