/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.enums;

/**
 *
 */
public enum PaymentNotificationStatus {
    ACCEPTED,
    REJECTED,
    UNSET;

    @Override
    public String toString() {
        return "com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus{" + '}';
    }
}
