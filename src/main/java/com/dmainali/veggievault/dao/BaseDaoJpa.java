package com.dmainali.veggievault.dao;

import java.util.List;
/**
 * This class implements BaseDao interface and overrides all its methods for CRUD operations.
 * The class is to be used as a base class for other DAO (data access object) classes that work with JPA.
 * @author Dirghayu Mainali
 * @version 1.0
 */

public class BaseDaoJpa implements BaseDao{
    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public Object findById(Long id) {
        return null;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Object entity) {

    }
}
