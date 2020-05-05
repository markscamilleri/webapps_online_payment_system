/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface Dao<T> {
    Optional<T> getById(long id);
    
    List< T> getAll();

    void create(T record);

    T update(T updatedRecord);
    
    void delete(T record);

    void deleteById(long id);
}
