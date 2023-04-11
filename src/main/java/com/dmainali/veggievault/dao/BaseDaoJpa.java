package com.dmainali.veggievault.dao;

import com.dmainali.veggievault.entity.BaseEntity;
import com.dmainali.veggievault.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.TransientObjectException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
/**
 * This class implements BaseDao interface and overrides all its methods for CRUD operations.
 * The class is to be used as a base class for other DAO (data access object) classes that work with JPA.
 * @author Dirghayu Mainali
 * @version 1.0
 */

@Slf4j
public class BaseDaoJpa<T extends BaseEntity> implements BaseDao<T>{

    private Class<T> entityClass;
    private String CLASS_NAME;

    @PersistenceContext(unitName = "default")
    protected EntityManager entityManager;

    public BaseDaoJpa(){
        Class<?> currentClass = getClass();
                Type c = currentClass.getGenericSuperclass();
                Type type = ((ParameterizedType)c).getActualTypeArguments()[0];
                    entityClass = (Class<T>)type;
        CLASS_NAME = entityClass.getSimpleName();
    }

    @Override
    public List<T> findAll() {
        String sql = "select c from "+CLASS_NAME+" c";
        Query query = entityManager.createQuery(sql);
        List<T> results = query.getResultList();
        return results;
    }

    @Override
    public T findById(Long id) {
        if(id == null){
            log.info("No id given, returning null");
        }
        T result = entityManager.find(entityClass, id);
        if(result == null) {
            log.info(CLASS_NAME + " with id " + id + " not found");
        }
        return result;
    }

    @Override
    @Transactional
    public T save(T entity) {
        T tmp = entity;
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.refresh(tmp);
        return tmp;
    }

//    @Override
//    public T save(T entity) {
//        T tmp = entity;
//        if(entity.getId() != null){
//            log.debug("     merge()");
//            tmp = entityManager.merge(entity);
//            entityManager.flush();
//
//            Object delegate = entityManager.getDelegate();
//            if(Session.class.isAssignableFrom(delegate.getClass())){
//                ((Session)delegate).refresh(tmp);
//            }else{
//                log.warn("Can't refresh: "+delegate.getClass() + "Is not a Session object");
//            }
//        }else{
//            log.debug("     persist()");
//            try{
//                entityManager.persist(entity);
//                entityManager.flush();
//                entityManager.refresh(tmp);
//            }catch (IllegalStateException e){
//                Throwable cause = e.getCause();
//                if(TransientObjectException.class.isAssignableFrom(cause.getClass())){
//                    throw new PersistenceException(cause.getMessage(),cause);
//                }
//                throw e;
//            }
//        }
//        return tmp;
//    }

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

    @Override
    public void delete(T entity) {}
}
