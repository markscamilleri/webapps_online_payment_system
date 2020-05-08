/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marks
 */
@Local
public interface CurrencyService {

    /**
     *
     * @return List of Currencies in the data source.
     */
    public List<CurrencyDto> getAllCurrencies();

    /**
     *
     * @param currencyId The currency's id
     * @return The currency transfer object for the specified currency
     * @throws com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException
     */
    public CurrencyDto getCurrencyById(long currencyId) throws CurrencyNotFoundException;

    /**
     *
     * @param name String with currency name
     * @return The currency transfer object for the specified currency
     * @throws com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException
     */
    public CurrencyDto getCurrencyByName(String name) throws CurrencyNotFoundException;

    /**
     *
     * @param shortName String with currency short name
     * @return The currency transfer object for the specified currency
     * @throws com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException
     */
    public CurrencyDto getCurrencyByShortName(String shortName) throws CurrencyNotFoundException;

    /**
     *
     * @param symbol String with currency symbol
     * @return The currency transfer object for the specified currency
     */
}
