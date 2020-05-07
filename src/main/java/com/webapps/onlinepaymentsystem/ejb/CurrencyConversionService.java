/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

/**
 *
 * @author marks
 */
public interface CurrencyConversionService {
    float convertCurrencyAmount(String currency1, String currency2, float amount);
}
