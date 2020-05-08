/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.entity.SystemUser;
import com.webapps.onlinepaymentsystem.entity.UserGroup_;
import com.webapps.onlinepaymentsystem.entity.SystemUser_;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 */
@Stateless
@Named("UserDao")
public class UserJpaDao extends JpaDao<SystemUser, UserDto> implements UserDao {

    @EJB
    private UserGroupJpaDao userGroupJpaDao1;

    @EJB
    private CurrencyJpaDao currencyJpaDao1;

    @EJB
    private CurrencyJpaDao currencyJpaDao;

    @EJB
    private UserGroupJpaDao userGroupJpaDao;

    @Override
    protected UserDto mapToDto(SystemUser record) {

        UserDto transferObject = new UserDto();
        transferObject.id = record.getId();
        transferObject.username = record.getUsername();
        transferObject.email = record.getEmail();
        transferObject.encryptedPassword = record.getEncryptedPassword();
        transferObject.lastLogin = record.getLastLogin();
        transferObject.registrationTimestamp = record.getRegistrationTimestamp();
        transferObject.userGroup = userGroupJpaDao.mapToDto(record.getUserGroup());
        
        return transferObject;
    }

    @Override
    protected SystemUser mapToRecord(UserDto transferObject) {

        SystemUser userRecord = new SystemUser();

        userRecord.setId(transferObject.id);
        userRecord.setUsername(transferObject.username);
        userRecord.setEmail(transferObject.email);
        userRecord.setEncryptedPassword(transferObject.encryptedPassword);
        userRecord.setLastLogin(transferObject.lastLogin);
        userRecord.setRegistrationTimestamp(transferObject.registrationTimestamp);
        userRecord.setUserGroup(userGroupJpaDao.getRecordById(transferObject.userGroup.id).orElse(userGroupJpaDao.mapToRecord(transferObject.userGroup)));

        return userRecord;
    }

    @Override
    public Optional<UserDto> getByUsername(String username) {
        return this.getByEqualsSingleParameter("username", username)
                .findFirst()
                .map(this::mapToDto);
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        return this.getByEqualsSingleParameter("email", email)
                .findFirst()
                .map(this::mapToDto);
    }

    @Override
    public List<UserDto> getByRoleName(String role) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root user = criteriaQuery.from(SystemUser.class);
        Join userGroup = user.join(SystemUser_.userGroup);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        userGroup.get(UserGroup_.name),
                        criteriaBuilder.parameter(String.class, "p_roleName")
                )
        );

        TypedQuery<SystemUser> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_role", role);

        return query.getResultStream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
