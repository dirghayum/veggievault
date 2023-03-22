package com.dmainali.veggievault.dao;

import com.dmainali.veggievault.entity.Vegetable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class represents a Data Access Object (DAO) for the Vegetable entity.
 * It uses the EntityManager to interact with the database and provide CRUD operations for Vegetable objects.
 * The class implements the BaseDao interface, which specifies the common CRUD operations for all DAOs.
 * @author Dirghayu Mainali
 * @version 1.0
 */

@Component
@Slf4j
public class VegetableDao implements BaseDao{

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    /**
     * This method finds all the Vegetable objects in the database and returns them as a List of Object.
     *
     * @return a List of Object representing all Vegetable objects in the database.
     */
    @Override
    public List<Object> findAll() {
        String sql = "Select v from item v";
        Query query = entityManager.createQuery(sql);
        List<Object> results = query.getResultList();
        return results;
    }

    /**
     * This method finds a Vegetable object in the database by its ID and returns it as an Object.
     *
     * @param id the ID of the Vegetable object to find.
     * @return an Object representing the Vegetable object with the given ID.
     */
    @Override
    public Object findById(Long id) {
        if(id == null){
            log.info("No id given, returning null");
        }
        Object result = entityManager.find(Vegetable.class, id);
        return result;
    }

    /**
     * This method saves a Vegetable object to the database.
     *
     * @param entity the Vegetable object to save to the database.
     * @return the saved Vegetable object.
     */
    @Override
    public Object save(Object entity) {
        Vegetable tmp = (Vegetable) entity;
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(tmp);
        return tmp;
    }

    /**
     * This method deletes a Vegetable object from the database by its ID.
     *
     * @param id the ID of the Vegetable object to delete from the database.
     */
    @Override
    public void delete(Long id) {
        if(id==null){
            return;
        }
        Vegetable tmp = entityManager.find(Vegetable.class, id);
        if(tmp == null || tmp.getId() == null){
            return;
        }
        entityManager.remove(tmp);
        entityManager.flush();
    }

    /**
     * This method deletes a Vegetable object from the database.
     *
     * @param entity the Vegetable object to delete from the database.
     */
    @Override
    public void delete(Object entity) {
        if(entity != null){
            Vegetable tmp = (Vegetable) entity;
            delete(tmp.getId());
            tmp.setId(null);
        }
    }
}
