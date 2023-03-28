package com.dmainali.veggievault.dao;

import com.dmainali.veggievault.entity.Vegetable;
import org.springframework.stereotype.Component;



/**
 * This class represents a Data Access Object (DAO) for the Vegetable entity.
 * It uses the EntityManager to interact with the database and provide CRUD operations for Vegetable objects.
 * The class implements the BaseDao interface, which specifies the common CRUD operations for all DAOs.
 * @author Dirghayu Mainali
 * @version 1.0
 */

@Component
public class VegetableDao extends BaseDaoJpa<Vegetable>{
}
