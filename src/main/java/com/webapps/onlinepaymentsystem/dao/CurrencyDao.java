/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.entity.Currency;
import java.util.Optional;

/**
 *
 */
public interface CurrencyDao extends Dao<Currency>{
    Optional<Currency> getByshortName(String shortName);
    
    Optional<Currency> getByName(String name);
}
