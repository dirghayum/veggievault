package com.dmainali.veggievault.dao;

import java.util.List;

public interface BaseDao {

    List<Object> findAll();

    Object findById(Long id);

    Object save(Object entity);

    void delete(Long id);

    void delete(Object entity);
}
