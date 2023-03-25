package com.dmainali.veggievault.service;

import com.dmainali.veggievault.dao.CategoryDao;
import com.dmainali.veggievault.entity.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class VeggieService {

    private CategoryDao categoryDao;

    public Category findById(Long id) {
        return (Category) categoryDao.findById(id);
    }
}
