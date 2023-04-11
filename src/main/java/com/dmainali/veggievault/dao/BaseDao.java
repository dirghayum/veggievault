package com.dmainali.veggievault.dao;


import java.util.List;

/**
 * The 'BaseDao' interface defines the common methods that are required for data access objects (DAOs).
 * It provides basic CRUD (create, read, update, delete) operations for interacting with a data source
 * @author Dirghayu Mainali
 * @version 1.0
 */
public interface BaseDao<T> {

    /**
     * This method retrieves all entities from the data source.
     * @return the list of objects that represents all entities in the data source.
     */
    List<T> findAll();

    /**
     * This method retrieves an entity from the data source by its ID.
     * @param id the ID of the entity to retrieve
     * @return Object representing the entity with the specified ID
     */
    T findById(Long id);

    /**
     * This method saves an entity to the data source.
     * @param entity the entity to save
     * @return  Object that represents the saved entity
     */
    T save(T entity);

    /**
     * This method deletes an entity from the data source by its ID.
     * @param id the ID of the entity to delete
     */
    void delete(Long id);

    /**
     * This method deletes an entity from the data source.
     * @param entity the entity to delete
     */
    void delete(T entity);
}
