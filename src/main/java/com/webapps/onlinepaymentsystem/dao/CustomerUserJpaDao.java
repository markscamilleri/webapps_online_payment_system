/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.CustomerUserDto;
import com.webapps.onlinepaymentsystem.entity.CustomerUser;
import java.util.Optional;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author marks
 */
public class CustomerUserJpaDao extends JpaDao<CustomerUser, CustomerUserDto> implements CustomerUserDao {

    @Override
    protected CustomerUserDto mapToDto(CustomerUser record) {
        CurrencyJpaDao cDao = new CurrencyJpaDao();
        UserJpaDao uDao = new UserJpaDao();

        CustomerUserDto transferObject = new CustomerUserDto();
        transferObject.id = record.getId();
        transferObject.user = uDao.mapToDto(record.getUser());
        transferObject.balance = record.getBalance();
        transferObject.currency = cDao.mapToDto(record.getCurrency());

        return transferObject;
    }

    @Override
    protected CustomerUser mapToRecord(CustomerUserDto transferObject) {
        CurrencyJpaDao cDao = new CurrencyJpaDao();
        UserJpaDao uDao = new UserJpaDao();

        CustomerUser userRecord = new CustomerUser();

        userRecord.setId(transferObject.id);
        userRecord.setUser(uDao.mapToRecord(transferObject.user));
        userRecord.setBalance(transferObject.balance);
        userRecord.setCurrency(cDao.mapToRecord(transferObject.currency));

        return userRecord;
    }

    @Override
    public Optional<CustomerUserDto> getByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root CustomerUser = criteriaQuery.from(CustomerUser.class);
        Join user = CustomerUser.join("user");

        criteriaQuery.where(
                criteriaBuilder.equal(
                        user.get("username"),
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

        Root CustomerUser = criteriaQuery.from(CustomerUser.class);
        Join user = CustomerUser.join("user");

        criteriaQuery.where(
                criteriaBuilder.equal(
                        user.get("email"),
                        criteriaBuilder.parameter(String.class, "p_email")
                )
        );

        TypedQuery<CustomerUser> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_email", email);

        return query.getResultStream().findFirst().map(this::mapToDto);
    }
}
