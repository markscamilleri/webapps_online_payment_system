/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.ejb;

import com.webapps.onlinepaymentsystem.dao.CurrencyJpaDao;
import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import com.webapps.onlinepaymentsystem.exceptions.CurrencyNotFoundException;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marks
 */
@Stateless
@RolesAllowed({"admin", "user"})
public class CurrencyServiceBean implements CurrencyService {

    @EJB
    private CurrencyJpaDao cDao;

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return cDao.getAll();
    }

    @Override
    public CurrencyDto getCurrencyByName(String name) throws CurrencyNotFoundException {
        return cDao.getByName(name).orElseThrow(() -> new CurrencyNotFoundException(name));
    }

    @Override
    public CurrencyDto getCurrencyByShortName(String shortName) throws CurrencyNotFoundException {
        return cDao.getByshortName(shortName).orElseThrow(() -> new CurrencyNotFoundException(shortName));
    }

    @Override
    public CurrencyDto getCurrencyById(long currencyId) throws CurrencyNotFoundException {
        return cDao.getById(currencyId).orElseThrow(() -> new CurrencyNotFoundException(Long.toString(currencyId)));
    }
}
