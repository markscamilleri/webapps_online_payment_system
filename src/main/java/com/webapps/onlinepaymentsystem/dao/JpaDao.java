/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.dao;

import com.webapps.onlinepaymentsystem.dto.Dto;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @param <T> The JPA entity class this uses
 * @param <S> The DTO class to use for input and output
 */
public abstract class JpaDao<T extends Serializable, S extends Dto> implements Dao<S> {
    
    private Class<T> entityClass;
    
    @PersistenceContext
    EntityManager entityManager;

    /**
     *
     * @param record The entity to map to a DTO
     * @return The DTO with the data copied from the record
     */
    protected abstract S mapToDto(T record);

    /**
     *
     * @param transferObject The DTO to map to an entity
     * @return Entity version of the transfer object.
     */
    protected abstract T mapToRecord(S transferObject);
    
    protected void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Searches for a record that matches the given parameter. Uses an equality
     * comparison
     *
     * @param <S> The Java Class type of the parameter
     * @param parameterKey The column/attribute to compare
     * @param parameterValue The value to compare it to
     * @return Optionally the entity record object matching this parameter.
     */
    protected <S> Stream<T> getByEqualsSingleParameter(final String parameterKey, final S parameterValue) {
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
        
        return query.getResultStream();
    }

    /**
     * Specifically gets the entity record by ID, useful for operations such as
     * {@link #deleteById(long) deleteById}
     *
     * @param id The record's id
     * @return Optionally the record
     */
    protected Optional<T> getRecordById(long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }
    
    @Override
    public Optional<S> getById(long id) {
        return this.getRecordById(id).map(this::mapToDto);
    }
    
    @Override
    public List<S> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        
        criteriaQuery.from(entityClass);
        
        return entityManager.createQuery(criteriaQuery)
                .getResultStream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void create(S record) {
        entityManager.persist(this.mapToRecord(record));
    }

    /**
     * Updates the record if, and only if, a mapping from the DTO to a record is
     * found
     *
     * @param updatedRecord DTO representing the record after the update.
     * @return Optional DTO representing the record after the update if there
     * was a record to update.
     */
    @Override
    public Optional<S> update(S updatedRecord) {
        Optional<T> entityRecord = this.getRecordById(updatedRecord.id);
        if (entityRecord.isPresent()) {
            /*
             * Creating a new record makes JPA assume "null" values are 
             * forgotten when merging and doesn't update values to null.
             */
            T newEntityRecord = this.mapToRecord(updatedRecord);
            return Optional.of(this.entityManager.merge(newEntityRecord)).map(this::mapToDto);
        }

        // Returns with updated values
        return Optional.ofNullable(null);
    }
    
    @Override
    public void delete(S record) {
        entityManager.remove(this.mapToRecord(record));
    }
    
    @Override
    public void deleteById(long id) {
        Optional<T> record = getRecordById(id);
        entityManager.remove(record);
    }
}
