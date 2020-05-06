/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.PaymentNotificationDto;
import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
import com.webapps.onlinepaymentsystem.enums.TimeCondition;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * PaymentNotificationDto
 */
public interface PaymentNotificationDao extends Dao<PaymentNotificationDto> {

    List<PaymentNotificationDto> getByTimestamp(LocalDateTime timestmap, TimeCondition when);
    
    List<PaymentNotificationDto> getByStatus(PaymentNotificationStatus status);
    
    Optional<List<PaymentNotificationDto>> getByRequestingUserId(long id);
    
    Optional<List<PaymentNotificationDto>> getByPayerId(long id);
}
