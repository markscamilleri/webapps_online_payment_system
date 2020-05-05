/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.entity.PaymentNotification;
import com.webapps.onlinepaymentsystem.enums.PaymentNotificationStatus;
import com.webapps.onlinepaymentsystem.enums.TimeCondition;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author marks
 */
public class PaymentNotificationJpaDao extends JpaDao<PaymentNotification> implements PaymentNotificationDao {

    @Override
    public List<PaymentNotification> getByTimestamp(Date timestmap, TimeCondition when) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root<PaymentNotification> root = criteriaQuery.from(PaymentNotification.class);
        Predicate condition;

        switch (when) {
            case BEFORE:
                condition = criteriaBuilder.lessThan(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(Date.class, "p_timestamp")
                );
                break;
            case BEFORE_OR_NOW:
                condition = criteriaBuilder.lessThanOrEqualTo(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(Date.class, "p_timestamp")
                );
                break;
            case AFTER_OR_NOW:
                condition = criteriaBuilder.greaterThanOrEqualTo(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(Date.class, "p_timestamp")
                );
                break;
            case AFTER:
                condition = criteriaBuilder.greaterThan(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(Date.class, "p_timestamp")
                );
                break;
            case NOW:
            default:
                condition = criteriaBuilder.equal(
                        root.get("notificationTimestamp"),
                        criteriaBuilder.parameter(Date.class, "p_timestamp")
                );
                break;
        }

        criteriaQuery.where(condition);

        TypedQuery<PaymentNotification> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter("p_timestamp", timestmap);

        return query.getResultList();
    }

    @Override
    public List<PaymentNotification> getByStatus(PaymentNotificationStatus status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
