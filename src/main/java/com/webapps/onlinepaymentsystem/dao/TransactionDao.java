/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.enums.TimeCondition;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author marks
 */
public interface TransactionDao extends Dao<TransactionDto> {
    List<TransactionDto> getByTimestamp(LocalDateTime timestmap, TimeCondition when);

    Optional<List<TransactionDto>> getBySenderId(long id);
    
    Optional<List<TransactionDto>> getByReceiverId(long id);
}
