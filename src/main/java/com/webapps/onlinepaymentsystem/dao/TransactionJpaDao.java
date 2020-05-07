/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.TransactionDto;
import com.webapps.onlinepaymentsystem.entity.Transaction;
import com.webapps.onlinepaymentsystem.entity.SystemUser;
import com.webapps.onlinepaymentsystem.enums.TimeCondition;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 */
public class TransactionJpaDao extends JpaDao<Transaction, TransactionDto> implements TransactionDao {

    @Override
    protected TransactionDto mapToDto(Transaction record) {
        TransactionDto transferObject = new TransactionDto();

        SystemUserJpaDao sysUserDao = new SystemUserJpaDao();
        CurrencyJpaDao cDao = new CurrencyJpaDao();

        transferObject.id = record.getId();
        transferObject.description = record.getDescription();
        transferObject.timestamp = record.getTxTimestamp();
        transferObject.fromUser = sysUserDao.mapToDto(record.getFromUser());
        transferObject.toUser = sysUserDao.mapToDto(record.getToUser());
        transferObject.sendAmount = record.getSendAmount();
        transferObject.sendCurrency = cDao.mapToDto(record.getSendCurrency());
        transferObject.recvAmount = record.getRecvAmount();
        transferObject.recvCurrency = cDao.mapToDto(record.getRecvCurrency());

        return transferObject;
    }

    @Override
    /**
     * Any changes to nested DTO classes are ignored
     */
    protected Transaction mapToRecord(TransactionDto transferObject) {
        Transaction record = new Transaction();

        SystemUserJpaDao sysUserDao = new SystemUserJpaDao();
        CurrencyJpaDao cDao = new CurrencyJpaDao();

        record.setId(transferObject.id);
        record.setDescription(transferObject.description);
        record.setTxTimestamp(transferObject.timestamp);
        record.setFromUser(sysUserDao.getRecordById(transferObject.fromUser.id).orElse(null));
        record.setToUser(sysUserDao.getRecordById(transferObject.toUser.id).orElse(null));
        record.setSendAmount(transferObject.sendAmount);
        record.setSendCurrency(cDao.getRecordById(transferObject.sendCurrency.id).orElse(null));
        record.setRecvAmount(transferObject.recvAmount);
        record.setRecvCurrency(cDao.getRecordById(transferObject.recvCurrency.id).orElse(null));

        return record;
    }

    @Override
    public List<TransactionDto> getByTimestamp(LocalDateTime timestmap, TimeCondition when) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        Predicate condition;

        switch (when) {
            case BEFORE:
                condition = criteriaBuilder.lessThan(
                        root.get("txTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case BEFORE_OR_NOW:
                condition = criteriaBuilder.lessThanOrEqualTo(
                        root.get("txTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case AFTER_OR_NOW:
                condition = criteriaBuilder.greaterThanOrEqualTo(
                        root.get("txTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case AFTER:
                condition = criteriaBuilder.greaterThan(
                        root.get("txTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case NOW:
            default:
                condition = criteriaBuilder.equal(
                        root.get("txTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
        }

        criteriaQuery.where(condition);

        TypedQuery<Transaction> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_timestamp", timestmap);

        return query.getResultStream()
                .map(this::mapToDto)
                .collect(Collectors.toList());        
    }

    @Override
    public Optional<List<TransactionDto>> getBySenderId(long id) {
        JpaDao uDao = new UserJpaDao();
        Optional<SystemUser> user = uDao.getRecordById(id);

        return user.map(
                presentUser -> presentUser.getTransactionsOut()
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<List<TransactionDto>> getByReceiverId(long id) {
        JpaDao uDao = new UserJpaDao();
        Optional<SystemUser> user = uDao.getRecordById(id);

        return user.map(
                presentUser -> presentUser.getTransactionsIn()
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList())
        );
    }

}
