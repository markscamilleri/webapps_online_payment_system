/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.enums;

/**
 *
 * @author marks
 */
public enum TimeCondition {
    BEFORE,
    AFTER,
    NOW,
    BEFORE_OR_NOW,
    AFTER_OR_NOW;

    @Override
    public String toString() {
        return "com.webapps.onlinepaymentsystem.enums.TimeCondition{" + '}';
    }
}
