/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.entity.Transaction;
import com.webapps.onlinepaymentsystem.entity.User;
import java.util.List;

/**
 *
 * @author marks
 */
public interface TransactionDao extends Dao<Transaction> {
    // see if getByUser is also there by default from UserDao
}
