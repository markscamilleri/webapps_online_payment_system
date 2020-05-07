/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.SystemUserDto;
import com.webapps.onlinepaymentsystem.entity.SystemUser;
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
public class SystemUserJpaDao extends JpaDao<SystemUser, SystemUserDto> implements SystemUserDao {

    @Override
    protected SystemUserDto mapToDto(SystemUser record) {
        CurrencyJpaDao cDao = new CurrencyJpaDao();
        UserJpaDao uDao = new UserJpaDao();

        SystemUserDto transferObject = new SystemUserDto();
        transferObject.id = record.getId();
        transferObject.user = uDao.mapToDto(record.getUser());
        transferObject.balance = record.getBalance();
        transferObject.currency = cDao.mapToDto(record.getCurrency());

        return transferObject;
    }

    @Override
    protected SystemUser mapToRecord(SystemUserDto transferObject) {
        CurrencyJpaDao cDao = new CurrencyJpaDao();
        UserJpaDao uDao = new UserJpaDao();

        SystemUser userRecord = new SystemUser();

        userRecord.setId(transferObject.id);
        userRecord.setUser(uDao.mapToRecord(transferObject.user));
        userRecord.setBalance(transferObject.balance);
        userRecord.setCurrency(cDao.mapToRecord(transferObject.currency));

        return userRecord;
    }

    @Override
    public Optional<SystemUserDto> getByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root systemUser = criteriaQuery.from(SystemUser.class);
        Join user = systemUser.join("user");

        criteriaQuery.where(
                criteriaBuilder.equal(
                        user.get("username"),
                        criteriaBuilder.parameter(String.class, "p_username")
                )
        );

        TypedQuery<SystemUser> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_username", username);

        return query.getResultStream().findFirst().map(this::mapToDto);
    }

    @Override
    public Optional<SystemUserDto> getByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root systemUser = criteriaQuery.from(SystemUser.class);
        Join user = systemUser.join("user");

        criteriaQuery.where(
                criteriaBuilder.equal(
                        user.get("email"),
                        criteriaBuilder.parameter(String.class, "p_email")
                )
        );

        TypedQuery<SystemUser> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_email", email);

        return query.getResultStream().findFirst().map(this::mapToDto);
    }
}
