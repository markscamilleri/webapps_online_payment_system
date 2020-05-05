/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author marks
 */
public abstract class JpaDao<T extends Serializable> {

    private Class<T> entityClass;

    @PersistenceContext
    EntityManager entityManager;

    protected void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected <S> Optional<T> getByEqualsSingleParameter(final String parameterKey, final S parameterValue) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        Root<S> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.where(
                criteriaBuilder.equal(
                        root.get(parameterKey),
                        criteriaBuilder.parameter(parameterValue.getClass(), parameterKey)
                )
        );

        TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        query.setParameter(parameterKey, parameterValue);

        // getSingleResult would otherwise throw an exception if there's no results
        return query.getResultStream().findFirst();
    }

    public Optional<T> getById(long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();

        criteriaQuery.from(entityClass);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public void create(T record) {
        entityManager.persist(record);
    }

    public T update(T updatedRecord) {
        return entityManager.merge(updatedRecord);
    }

    public void delete(T record) {
        entityManager.remove(record);
    }

    public void deleteById(long id) {
        Optional<T> record = getById(id);
        record.ifPresent(this::delete);
    }
}
