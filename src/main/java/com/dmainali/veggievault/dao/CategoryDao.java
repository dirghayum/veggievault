package com.dmainali.veggievault.dao;

import com.dmainali.veggievault.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class represents a Data Access Object (DAO) for the Category entity.
 * It uses the EntityManager to interact with the database and provide CRUD operations for Category objects.
 * The class implements the BaseDao interface, which specifies the common CRUD operations for all DAOs.
 * @author Dirghayu Mainali
 * @version 1.0
 */

@Component
@Slf4j
public class CategoryDao implements BaseDao{

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    /**
     * This method finds all the Category objects in the database and returns them as a List of Object.
     *
     * @return a List of Object representing all Category objects in the database.
     */
    @Override
    public List<Object> findAll() {
        String sql = "select c from category c";
        Query query = entityManager.createQuery(sql);
        List<Object> results = query.getResultList();
        return results;
    }

    /**
     * This method finds a Category object in the database by its ID and returns it as an Object.
     *
     * @param id the ID of the Category object to find.
     * @return an Object representing the Category object with the given ID.
     */
    @Override
    public Object findById(Long id) {
        if(id == null){
            log.info("No id given, returning null");
        }
        Object result = entityManager.find(Category.class, id);
        return result;
    }


    /**
     * This method saves a Category object to the database.
     *
     * @param entity the Category object to save to the database.
     * @return the saved Category object.
     */
    @Override
    public Object save(Object entity) {
        Category tmp = (Category) entity;
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(tmp);
        return tmp;
    }

    /**
     * This method deletes a Category object from the database by its ID.
     *
     * @param id the ID of the Category object to delete from the database.
     */
    @Override
    public void delete(Long id) {
        if(id==null){
            return;
        }
        Category tmp = entityManager.find(Category.class, id);
        if(tmp == null || tmp.getId() == null){
            return;
        }
        entityManager.remove(tmp);
        entityManager.flush();
    }


    /**
     * This method deletes a Category object from the database.
     *
     * @param entity the Category object to delete from the database.
     */
    @Override
    public void delete(Object entity) {
        if(entity != null){
            Category tmp = (Category) entity;
            delete(tmp.getId());
            tmp.setId(null);
        }
    }
}
