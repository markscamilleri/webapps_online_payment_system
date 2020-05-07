/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.http.CurrencyConversionClient;
import com.webapps.onlinepaymentsystem.http.CurrencyConversionResponse;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author marks
 */
@Named(value = "currency")
@ApplicationScoped
public class CurrencyConversionBean implements CurrencyConversionService {

    /**
     * Creates a new instance of Currency
     */
    public CurrencyConversionBean() {
    }

    @Override
    public float convertCurrencyAmount(String currency1, String currency2, float amount) {
        CurrencyConversionClient ccc = new CurrencyConversionClient(currency1, currency2, Float.toString(amount));
        CurrencyConversionResponse response = ccc.getJson_JSON(CurrencyConversionResponse.class);
        
        return response.amount;    
    }
}
