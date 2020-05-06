/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.AdminUserDto;
import com.webapps.onlinepaymentsystem.entity.AdminUser;
import java.util.Optional;

/**
 *
 * @author marks
 */
public class AdminUserJpaDao extends JpaDao<AdminUser, AdminUserDto> implements AdminUserDao {

    @Override
    protected AdminUserDto mapToDto(AdminUser record) {
        AdminUserDto dto = new AdminUserDto();

        dto.id = record.getId().longValue();
        dto.username = record.getUsername();
        dto.email = record.getEmail();
        dto.encryptedPassword = record.getEncryptedPassword();
        dto.lastLogin = record.getLastLogin();
        dto.registrationTimestamp = record.getRegistrationTimestamp();

        return dto;
    }

    @Override
    protected AdminUser mapToRecord(AdminUserDto transferObject) {
        AdminUser entityRecord = new AdminUser();

        entityRecord.setId(transferObject.id);
        entityRecord.setEmail(transferObject.email);
        entityRecord.setUsername(transferObject.username);
        entityRecord.setRegistrationTimestamp(transferObject.registrationTimestamp);
        entityRecord.setLastLogin(transferObject.lastLogin);
        entityRecord.setEncryptedPassword(transferObject.encryptedPassword);

        return entityRecord;
    }

    @Override
    public Optional<AdminUserDto> getByUsername(String username) {
        // This should only get one reesult since username is unique
        return this.getByEqualsSingleParameter("username", username)
                .map(this::mapToDto)
                .findFirst();
    }

    @Override
    public Optional<AdminUserDto> getByEmail(String email) {
        // This should only get one reesult since email is unique
        return this.getByEqualsSingleParameter("email", email)
                .map(this::mapToDto)
                .findFirst();
    }
}
