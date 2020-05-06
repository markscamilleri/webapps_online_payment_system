/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.Dto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T> The Data Transfer Object type
 */
public interface Dao<T extends Dto> {
    Optional<T> getById(long id);
    
    List<T> getAll();

    void create(T record);

    Optional<T> update(T updatedRecord);
    
    void delete(T record);

    void deleteById(long id);
    
}
