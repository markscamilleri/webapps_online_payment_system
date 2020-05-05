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
 * @author marks
 */
public class CurrencyJpaDao extends JpaDao<Currency> implements CurrencyDao {

    @Override
    public Optional<Currency> getByshortName(String shortName) {
        return this.getByEqualsSingleParameter("shortName", shortName);
    }

    @Override
    public Optional<Currency> getByName(String name) {
        return this.getByEqualsSingleParameter("name", name);
    }
    
}
