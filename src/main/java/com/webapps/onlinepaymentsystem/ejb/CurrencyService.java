/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import java.util.List;
import java.util.Optional;
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
     * @param name String with currency name
     * @return The currency transfer object for the specified currency
     */
    public Optional<CurrencyDto> getCurrencyByName(String name);

    /**
     *
     * @param shortName String with currency short name
     * @return The currency transfer object for the specified currency
     */
    public Optional<CurrencyDto> getCurrencyByShortName(String shortName);

    /**
     *
     * @param symbol String with currency symbol
     * @return The currency transfer object for the specified currency
     */
}
