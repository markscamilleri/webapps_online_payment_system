/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.CurrencyDao;
import com.webapps.onlinepaymentsystem.dao.CurrencyJpaDao;
import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author marks
 */
@ApplicationScoped
public class CurrencyServiceBean implements CurrencyService {
    
    private CurrencyDao cDao = new CurrencyJpaDao();
    
    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return cDao.getAll();
    }

    @Override
    public Optional<CurrencyDto> getCurrencyByName(String name) {
        return cDao.getByName(name);
    }

    @Override
    public Optional<CurrencyDto> getCurrencyByShortName(String shortName) {
        return cDao.getByshortName(shortName);
    }
}