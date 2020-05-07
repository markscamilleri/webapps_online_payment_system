/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.UserGroupDto;
import com.webapps.onlinepaymentsystem.entity.UserGroup;
import java.util.List;
import java.util.Optional;

public class UserGroupJpaDao extends JpaDao<UserGroup, UserGroupDto> implements UserGroupDao {

    @Override
    protected UserGroupDto mapToDto(UserGroup record) {
        UserGroupDto transferObject = new UserGroupDto();

        transferObject.id = record.getId();
        transferObject.name = record.getName();

        return transferObject;
    }

    @Override
    protected UserGroup mapToRecord(UserGroupDto transferObject) {
        UserGroup record = new UserGroup();

        record.setId(transferObject.id);
        record.setName(transferObject.name);

        return record;
    }

    @Override
    public Optional<UserGroupDto> getByName(String name) {
        return this.getByEqualsSingleParameter("name", name)
                .findFirst()
                .map(this::mapToDto);
    }
}
