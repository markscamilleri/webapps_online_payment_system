/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.PaymentNotificationDto;
import com.webapps.onlinepaymentsystem.entity.PaymentNotification;
import com.webapps.onlinepaymentsystem.entity.User;
import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
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
 * @author marks
 */
public class PaymentNotificationJpaDao extends JpaDao<PaymentNotification, PaymentNotificationDto> implements PaymentNotificationDao {

    @Override
    protected PaymentNotificationDto mapToDto(PaymentNotification record) {
        PaymentNotificationDto transferObject = new PaymentNotificationDto();

        UserJpaDao uDao = new UserJpaDao();
        CurrencyJpaDao cDao = new CurrencyJpaDao();

        transferObject.id = record.getId();
        transferObject.requestingUser = uDao.mapToDto(record.getRequestingUser());
        transferObject.payer = uDao.mapToDto(record.getPayer());
        transferObject.amount = record.getAmount();
        transferObject.currency = cDao.mapToDto(record.getCurrency());
        transferObject.description = record.getDescription();
        transferObject.status = record.getStatus();
        transferObject.notificationTimestamp = record.getNotificationTimestamp();

        return transferObject;
    }

    @Override
    protected PaymentNotification mapToRecord(PaymentNotificationDto transferObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaymentNotificationDto> getByTimestamp(LocalDateTime timestmap, TimeCondition when) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root<PaymentNotification> root = criteriaQuery.from(PaymentNotification.class);
        Predicate condition;

        switch (when) {
            case BEFORE:
                condition = criteriaBuilder.lessThan(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case BEFORE_OR_NOW:
                condition = criteriaBuilder.lessThanOrEqualTo(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case AFTER_OR_NOW:
                condition = criteriaBuilder.greaterThanOrEqualTo(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case AFTER:
                condition = criteriaBuilder.greaterThan(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
            case NOW:
            default:
                condition = criteriaBuilder.equal(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(LocalDateTime.class, "p_timestamp")
                );
                break;
        }

        criteriaQuery.where(condition);

        TypedQuery<PaymentNotification> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_timestamp", timestmap);

        return query.getResultStream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentNotificationDto> getByStatus(PaymentNotificationStatus status) {
        return this.getByEqualsSingleParameter("status", status)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param id User entity record id
     * @return Optionally the payment requests this user made if the user exists
     * exists.
     */
    @Override
    public Optional<List<PaymentNotificationDto>> getByRequestingUserId(long id) {
        JpaDao uDao = new UserJpaDao();
        Optional<User> user = uDao.getRecordById(id);

        return user.map(
                presentUser -> presentUser.getNotificationsSent()
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 
     * @param id User entity record id
     * @return Optionally the payment requests this user received if the user exists
     */
    @Override
    public Optional<List<PaymentNotificationDto>> getByPayerId(long id) {
        JpaDao uDao = new UserJpaDao();
        Optional<User> user = uDao.getRecordById(id);

        return user.map(
                presentUser -> presentUser.getNotificationsRecv()
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList())
        );
    }

}
