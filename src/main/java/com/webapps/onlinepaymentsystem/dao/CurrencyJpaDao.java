/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.CurrencyDto;
import com.webapps.onlinepaymentsystem.entity.Currency;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author marks
 */
@Stateless
@Named("CurrencyJpaDao")
public class CurrencyJpaDao extends JpaDao<Currency, CurrencyDto> implements CurrencyDao {

    @Override
    public Optional<CurrencyDto> getByshortName(String shortName) {
        // This should only get one reesult since shortName is unique
        return this.getByEqualsSingleParameter("shortName", shortName)
                .map(this::mapToDto)
                .findFirst();
    }

    @Override
    public Optional<CurrencyDto> getByName(String name) {
        // This should only get one reesult since name is unique
        return this.getByEqualsSingleParameter("name", name)
                .map(this::mapToDto)
                .findFirst();
    }

    @Override
    protected CurrencyDto mapToDto(Currency record) {
        CurrencyDto dto = new CurrencyDto();
        dto.id = record.getId();
        dto.name = record.getName();
        dto.shortName = record.getShortName();
        dto.symbol = record.getSymbol();
        
        return dto;
    }

    @Override
    protected Currency mapToRecord(CurrencyDto transferObject) {
        Currency entityRecord = new Currency();
        
        entityRecord.setId(transferObject.id);
        entityRecord.setName(transferObject.name);
        entityRecord.setShortName(transferObject.shortName);
        entityRecord.setSymbol(transferObject.symbol);
        
        return entityRecord;
    }
}
