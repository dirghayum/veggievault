package com.dmainali.veggievault.service;

import com.dmainali.veggievault.dao.CategoryDao;
import com.dmainali.veggievault.dao.VegetableDao;
import com.dmainali.veggievault.entity.Category;
import com.dmainali.veggievault.entity.Vegetable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VeggieService {

    private CategoryDao categoryDao;
    private VegetableDao vegetableDao;

    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    public List<Vegetable> findAllVegetables(){
        return vegetableDao.findAll();
    }
}
