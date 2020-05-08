/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.entity.CustomerUser;
import com.webapps.onlinepaymentsystem.entity.CustomerUser_;
import com.webapps.onlinepaymentsystem.entity.SystemUser_;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author marks
 */
@Stateless
@Named("CustomerUserDao")
public class CustomerUserJpaDao extends JpaDao<CustomerUser, CustomerUserDto> implements CustomerUserDao {

    @EJB
    private UserJpaDao userDao;
    
    @EJB
    private CurrencyJpaDao currencyDao;
    
    @Override
    protected CustomerUserDto mapToDto(CustomerUser record) {

        CustomerUserDto transferObject = new CustomerUserDto();
        transferObject.id = record.getId();
        transferObject.user = userDao.mapToDto(record.getUser());
        transferObject.balance = record.getBalance();
        transferObject.currency = currencyDao.mapToDto(record.getCurrency());

        return transferObject;
    }

    @Override
    protected CustomerUser mapToRecord(CustomerUserDto transferObject) {
        CustomerUser userRecord = new CustomerUser();

        userRecord.setId(transferObject.id);
        userRecord.setUser(userDao.mapToRecord(transferObject.user));
        userRecord.setBalance(transferObject.balance);
        userRecord.setCurrency(currencyDao.mapToRecord(transferObject.currency));

        return userRecord;
    }

    @Override
    public Optional<CustomerUserDto> getByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root customerUser = criteriaQuery.from(CustomerUser.class);
        Join user = customerUser.join(CustomerUser_.user);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        user.get(SystemUser_.username),
                        criteriaBuilder.parameter(String.class, "p_username")
                )
        );

        TypedQuery<CustomerUser> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_username", username);

        return query.getResultStream().findFirst().map(this::mapToDto);
    }

    @Override
    public Optional<CustomerUserDto> getByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root customerUser = criteriaQuery.from(CustomerUser.class);
        Join user = customerUser.join(CustomerUser_.user);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        user.get(SystemUser_.email),
                        criteriaBuilder.parameter(String.class, "p_email")
                )
        );

        TypedQuery<CustomerUser> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_email", email);

        return query.getResultStream().findFirst().map(this::mapToDto);
    }
}
