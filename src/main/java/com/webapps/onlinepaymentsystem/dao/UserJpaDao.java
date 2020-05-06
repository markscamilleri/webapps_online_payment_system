/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.UserDto;
import com.webapps.onlinepaymentsystem.entity.User;
import java.util.Optional;

/**
 *
 */
public class UserJpaDao extends JpaDao<User, UserDto> implements UserDao {

    @Override
    protected UserDto mapToDto(User record) {
        CurrencyJpaDao cDao = new CurrencyJpaDao();

        UserDto transferObject = new UserDto();
        transferObject.id = record.getId();
        transferObject.username = record.getUsername();
        transferObject.email = record.getEmail();
        transferObject.encryptedPassword = record.getEncryptedPassword();
        transferObject.balance = record.getBalance();
        transferObject.currency = cDao.mapToDto(record.getCurrency());
        transferObject.lastLogin = record.getLastLogin();
        transferObject.registrationTimestamp = record.getRegistrationTimestamp();

        return transferObject;
    }

    @Override
    protected User mapToRecord(UserDto transferObject) {
        CurrencyJpaDao cDao = new CurrencyJpaDao();

        User userRecord = new User();

        userRecord.setId(transferObject.id);
        userRecord.setUsername(transferObject.username);
        userRecord.setEmail(transferObject.email);
        userRecord.setEncryptedPassword(transferObject.encryptedPassword);
        userRecord.setBalance(transferObject.balance);
        userRecord.setCurrency(cDao.mapToRecord(transferObject.currency));
        userRecord.setLastLogin(transferObject.lastLogin);
        userRecord.setRegistrationTimestamp(transferObject.registrationTimestamp);

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
}
