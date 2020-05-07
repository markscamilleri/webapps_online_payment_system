/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.conversion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "conversion")
public class CurrencyConversion {
    private String currency;
    private float amount;

    @XmlAttribute
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency){
        this.currency = currency;
    }

    @XmlAttribute
    public float getAmount() {
        return amount;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }
}
